package com.example.countries.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class CapitalInfo(
    val latlng: List<Double>
){
    @PrimaryKey(autoGenerate = true)
    var capid =0
}