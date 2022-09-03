package com.example.countries.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.countries.data.remote.dto.CountryDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM countrydto")
    fun getAll(): Flow<List<CountryDto>>

    @Insert()
    fun addAll(list : List<CountryDto>)

    @Query("DELETE FROM countrydto")
    fun removeAll()


}