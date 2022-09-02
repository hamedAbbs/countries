package com.example.countries.data.repository

import com.example.countries.common.Consts.BASE_URL
import com.example.countries.common.Consts.COUNTRIES_URL
import com.example.countries.common.getStringFromURL
import com.example.countries.data.remote.dto.CountryDto
import com.example.countries.data.room.LocalDB
import com.example.countries.domain.repository.CountryRepo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class CountryRepoImpl @Inject constructor(
    private val coroutineScope: CoroutineScope,
    private val localDB: LocalDB
) : CountryRepo {

    override val countries: StateFlow<List<CountryDto>> =  localDB
        .countriesDao()
        .getAll()
        .stateIn(coroutineScope, SharingStarted.Eagerly, emptyList())


    init {
        coroutineScope.launch {
            try {
                getCoutries()
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun getCoutries() {
        val founderListType = object : TypeToken<ArrayList<CountryDto>>() {}.type
        val jsonObjectFromURL = getStringFromURL(COUNTRIES_URL);
        val list = Gson().fromJson<List<CountryDto>?>(jsonObjectFromURL, founderListType)
        localDB.countriesDao().removeAll()
        localDB.countriesDao().addAll(list)
    }

    private suspend fun getCountry(id:String): CountryDto {
        val founderListType = object : TypeToken<ArrayList<CountryDto>>() {}.type
        val jsonObjectFromURL = getStringFromURL("$BASE_URL/v3.1/alpha/$id")
        val list = Gson().fromJson<List<CountryDto>?>(jsonObjectFromURL, founderListType)
       return list[0]
    }

    override suspend fun getCountryByName(name: String): CountryDto {
        return countries.value.firstOrNull { it.cca2 == name } ?: getCountry(name)
    }


    override fun stop() {
        coroutineScope.cancel()
    }
}