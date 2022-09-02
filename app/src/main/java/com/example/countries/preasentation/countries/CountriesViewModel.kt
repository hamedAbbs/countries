package com.example.countries.preasentation.countries



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.Consts.UNEXPECTED_ERROR
import com.example.countries.common.Resource
import com.example.countries.domain.use_case.getCountries.GetCountriesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel()
class CountriesViewModel @Inject constructor(
    private val getCountriesUC: GetCountriesUC
) : ViewModel(){

    private val _state = MutableStateFlow<CountriesState>(CountriesState.Loading)
    val state:StateFlow<CountriesState>  = _state

    init {
        getCountries()
    }

    private fun getCountries(){
        getCountriesUC().onEach { res ->
            when(res){
                is Resource.Success -> {
                    _state.value = CountriesState.Success(countries = res.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CountriesState.Error(error = res.message ?: UNEXPECTED_ERROR)
                }
                is Resource.Loading -> {
                    _state.value = CountriesState.Loading
                }
            }

        }.launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        getCountriesUC.stop()
    }
}