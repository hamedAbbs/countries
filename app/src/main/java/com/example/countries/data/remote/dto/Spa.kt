package com.example.countries.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Spa(

    val commonSpa: String?,
    val officialSpa: String?
){
    @PrimaryKey(autoGenerate = true)
    var spaid =0
}