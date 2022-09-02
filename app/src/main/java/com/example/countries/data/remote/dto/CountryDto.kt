package com.example.countries.data.remote.dto

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countries.domain.model.Country

@Entity
data class CountryDto(
    val capital: List<String>?,
    @Embedded
    val capitalInfo: CapitalInfo?,
    @PrimaryKey
    val cca2: String,
    val flag: String?,
    @Embedded
    val flags: Flags?,
    @Embedded
    val languages: Languages?,
    @ColumnInfo(name="latlong")
    val latlng: List<Double>,
    @Embedded
    val maps: Maps?,
    @Embedded
    val name: Name?,
    val population: Int,
    val region: String?,
    val subregion: String?,
)

fun CountryDto.toCountry() : Country{
    return Country(cca2 =cca2,
        capital = capital,
        latlng = latlng,
        capitalInfo = capitalInfo ,
        flag = flag,
        flags = flags,
        languages = languages,
        maps = maps,
        name = name,
        population = population,
        region = region,
        subregion = subregion
    )
}