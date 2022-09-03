package com.example.countries.preasentation

import com.example.countries.common.Consts.COUNTRIES_SCREEN
import com.example.countries.common.Consts.COUNTRY_SCREEN

sealed class Screen(val route: String){
    object countriesScreen : Screen(COUNTRIES_SCREEN)
    object countryScreen : Screen(COUNTRY_SCREEN)
}
