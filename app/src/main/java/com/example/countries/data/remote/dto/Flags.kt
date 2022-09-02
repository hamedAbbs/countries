package com.example.countries.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flags(
    val png: String,
    val svg: String
){
    @PrimaryKey(autoGenerate = true)
    var flagsid =0
}