package com.example.countries.domain.repository

import com.example.countries.data.remote.dto.CountryDto
import kotlinx.coroutines.flow.StateFlow


// a repository interface we can use it for testing purposes
interface CountryRepo {

    suspend fun getCountryByName(name: String) : CountryDto

    val countries: StateFlow<List<CountryDto>>

    fun stop()
}