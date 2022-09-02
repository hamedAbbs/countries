package com.example.countries.preasentation.countryDetail

import com.example.countries.domain.model.Country

data class CountryState(
    val isLoading: Boolean = false,
    val country: Country? = null,
    val error: String = ""
    )
