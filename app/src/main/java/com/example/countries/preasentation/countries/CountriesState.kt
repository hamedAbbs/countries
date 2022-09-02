package com.example.countries.preasentation.countries

import com.example.countries.domain.model.Country

sealed class CountriesState{
    object Loading:CountriesState()
    data class Error(val error : String = "" ):CountriesState()
    data class Success(val countries : List<Country> = emptyList()):CountriesState()
}
