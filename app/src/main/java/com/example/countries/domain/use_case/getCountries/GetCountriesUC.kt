package com.example.countries.domain.use_case.getCountries

import com.example.countries.common.Consts.UNEXPECTED_ERROR
import com.example.countries.common.Resource
import com.example.countries.data.remote.dto.toCountry
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.CountryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountriesUC @Inject constructor(
    private val repository : CountryRepo
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow{
        try {
            emit(Resource.Loading())
            repository.countries.map { list -> list.map { it.toCountry() } }.collect{
                emit(Resource.Success(it))
            }
        } catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR))
        }
    }

    fun stop(){
        repository.stop()
    }
}