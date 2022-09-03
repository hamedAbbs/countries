package com.example.countries.di

import android.content.Context
import androidx.room.Room
import com.example.countries.data.repository.CountryRepoImpl
import com.example.countries.data.room.LocalDB
import com.example.countries.domain.repository.CountryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountriesRepository(localDB: LocalDB): CountryRepo{
        return CountryRepoImpl(CoroutineScope(Dispatchers.IO), localDB)
    }

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext context: Context): LocalDB {
        return Room.databaseBuilder(
            context,
            LocalDB::class.java, "countries"
        ).fallbackToDestructiveMigration().build()
    }
}