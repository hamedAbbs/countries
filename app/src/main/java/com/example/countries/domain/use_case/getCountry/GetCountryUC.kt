package com.example.countries.domain.use_case.getCountry

import com.example.countries.common.Consts.UNEXPECTED_ERROR
import com.example.countries.common.Resource
import com.example.countries.data.remote.dto.toCountry
import com.example.countries.domain.model.Country
import com.example.countries.domain.repository.CountryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryUC @Inject constructor(
    private val repository : CountryRepo
) {
    operator fun invoke(countryName: String): Flow<Resource<Country>> = flow{
        try {
            emit(Resource.Loading())
            val country = repository.getCountryByName(countryName).toCountry()
            emit(Resource.Success<Country>(country))
        } catch (e: Exception){
            emit(Resource.Error(e.localizedMessage ?: UNEXPECTED_ERROR))
        }
    }
}