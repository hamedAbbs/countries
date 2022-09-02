package com.example.countries.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Languages(
    val spa: String?
){
    @PrimaryKey(autoGenerate = true)
    var languagesId =0
}