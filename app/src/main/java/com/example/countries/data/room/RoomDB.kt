package com.example.countries.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.countries.data.remote.dto.*
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromList(value : List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String?) = value?.let {  Gson().fromJson(value,Array<String>::class.java)?.toList()}?: emptyList()


}

class DConverter{
    @TypeConverter
    fun fromList(value : List<Double>?) = Gson().toJson(value)

    @TypeConverter
    fun toList(value: String?) = value?.let { Gson().fromJson(value,Array<Double>::class.java)?.toList()}?: emptyList()
}
@Database(entities = [CountryDto::class,CapitalInfo::class,Languages::class,Name::class,Maps::class,Flags::class], version = 6)
@TypeConverters(Converters::class,DConverter::class)
abstract class LocalDB : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}