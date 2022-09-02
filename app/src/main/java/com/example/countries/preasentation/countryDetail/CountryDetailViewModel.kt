package com.example.countries.preasentation.countryDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.common.Consts
import com.example.countries.common.Consts.COUNTRY_NAME
import com.example.countries.common.Resource
import com.example.countries.domain.model.Country
import com.example.countries.domain.use_case.getCountry.GetCountryUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryUC: GetCountryUC,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _state = mutableStateOf(CountryState())
    val state :State<CountryState> = _state


    init {
        savedStateHandle.get<String>(COUNTRY_NAME)?.let {
            getCountry(it)
        }
    }

    private fun getCountry(countryName : String){
        getCountryUC(countryName).onEach { res ->
            when(res){
                is Resource.Success -> {
                    _state.value = CountryState(country = res.data)
                }
                is Resource.Error -> {
                    _state.value = CountryState(error = res.message ?: Consts.UNEXPECTED_ERROR)
                }
                is Resource.Loading -> {
                    _state.value = CountryState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}