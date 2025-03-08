import br.com.jwar.habittracker.data.datasource.HabitsLocalDatasource
import br.com.jwar.habittracker.data.datasource.HabitsMemoryDatasource
import br.com.jwar.habittracker.data.repository.HabitsRepositoryImpl
import br.com.jwar.habittracker.domain.model.HabitPeriod
import br.com.jwar.habittracker.domain.model.HabitStatus
import br.com.jwar.habittracker.domain.repository.HabitsRepository
import br.com.jwar.habittracker.presentation.ui.HabitsIntent
import br.com.jwar.habittracker.presentation.ui.HabitsViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class HabitsViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var habitsDataSource: HabitsLocalDatasource
    private lateinit var habitsRepository: HabitsRepository
    private lateinit var subject: HabitsViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        habitsDataSource = HabitsMemoryDatasource()
        habitsRepository = HabitsRepositoryImpl(habitsDataSource)
        subject = HabitsViewModel(habitsRepository)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `on init should load habits from data source`() = runBlocking(testDispatcher) {
        habitsRepository.addHabit("Drink water")

        subject.handleIntent(HabitsIntent.Init)

        val states = subject.state.take(1).toList()
        assertEquals(1, states[0].habits.size)
    }

    @Test
    fun `on add habit intent should adds a new habit to data source`() = runBlocking(testDispatcher) {
        subject.handleIntent(HabitsIntent.AddHabit("Drink water"))

        val states = subject.state.take(2).toList()
        assertEquals(0, states[0].habits.size)
        assertEquals(1, states[1].habits.size)
    }

    @Test
    fun `on change habit status intent should change habit status in data source`() = runBlocking(testDispatcher) {
        val habitName = "Drink water"
        val date = LocalDate(2021, 1, 1)
        val status = HabitStatus.DONE
        val habitId = habitsRepository.addHabit(habitName).getOrNull()?.id.orEmpty()

        subject.handleIntent(
            HabitsIntent.ChangeHabitStatus(
            habitId = habitId,
            day = date,
            status = status,
        ))

        val states = subject.state.take(3).toList()
        assertEquals(status, states[2].habits.first().history[date])
    }

    @Test
    fun `on change period intent should change selected period in state`() = runBlocking(testDispatcher) {
        subject.handleIntent(HabitsIntent.ChangePeriod(HabitPeriod.YEAR))

        val states = subject.state.take(1).toList()
        assertEquals(HabitPeriod.YEAR, states[0].selectedPeriod)
    }
}