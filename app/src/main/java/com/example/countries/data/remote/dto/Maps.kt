package com.example.countries.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
){
    @PrimaryKey(autoGenerate = true)
    var mapsid =0
}