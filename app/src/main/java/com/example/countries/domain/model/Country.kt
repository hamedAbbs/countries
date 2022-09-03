package com.example.countries.domain.model

import com.example.countries.data.remote.dto.*


data class Country(val cca2: String?,
                   val capital: List<String>?= emptyList(),
                   val latlng: List<Double>,
                   val capitalInfo: CapitalInfo?,
                   val flag: String?,
                   val flags: Flags?,
                   val languages: Languages?,
                   val maps: Maps?,
                   val name: Name?,
                   val population: Int,
                   val region: String?,
                   val subregion: String?) {

}