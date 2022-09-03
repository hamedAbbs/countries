package com.example.countries.data.remote.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Name(
    val commonName: String?,
    val official: String
){
    @PrimaryKey(autoGenerate = true)
    var nameid =0
}