package com.example.countries

import com.example.countries.data.remote.dto.CountryDto
import com.example.countries.domain.repository.CountryRepo
import com.example.countries.domain.use_case.getCountries.GetCountriesUC
import com.example.countries.preasentation.countries.CountriesState
import com.example.countries.preasentation.countries.CountriesViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.*
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Error

@OptIn(ExperimentalCoroutinesApi::class)
class CoutriesVmTest {
    private lateinit var mainThreadSurrogate: ExecutorCoroutineDispatcher

    lateinit var  uut:CountriesViewModel
    @OptIn(DelicateCoroutinesApi::class)
    @Before
    fun setUp() {
        mainThreadSurrogate= newSingleThreadContext("UI thread")
        Dispatchers.setMain(mainThreadSurrogate)

        uut = CountriesViewModel(GetCountriesUC(object : CountryRepo {

            override suspend fun getCountryByName(name: String): CountryDto {
                TODO()
            }

            override val countries: StateFlow<List<CountryDto>>
                get() = MutableStateFlow(emptyList())

            override fun stop() {
                TODO("Not yet implemented")
            }
        }))
    }

    @Test
    fun `Initial state - Loading`() = runTest{
       assert( uut.state.value is CountriesState.Loading)
    }

    @Test
    fun `Initial state - success after loading`() = runTest{
        val states = mutableListOf<CountriesState>()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            uut.state.toList(states)
        }

        advanceUntilIdle()

            println(states)
        assert(states[0] is CountriesState.Success)

        collectJob.cancel()

    }

    @Test
    fun `Initial state - shoulld emit error`() = runTest{
        uut = CountriesViewModel(GetCountriesUC(object : CountryRepo {

            override suspend fun getCountryByName(name: String): CountryDto {
                TODO()
            }

            override val countries: StateFlow<List<CountryDto>>
                get() = throw RuntimeException("Error")

            override fun stop() {
                TODO("Not yet implemented")
            }
        }))

        val states = mutableListOf<CountriesState>()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            uut.state.toList(states)
        }

        advanceUntilIdle()

        println(states)
        assert(states[0] is CountriesState.Error)
        assertEquals("Error",(states[0] as CountriesState.Error).error)

        collectJob.cancel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

}