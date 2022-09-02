package com.example.countries.data.remote.dto

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NativeName(
@Embedded
    val spaNativeName: Spa
){
    @PrimaryKey(autoGenerate = true)
    var nativeameid =0
}