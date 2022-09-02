package com.example.countries.preasentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countries.common.Consts.COUNTRY_NAME
import com.example.countries.preasentation.countries.comps.CountriesScreen
import com.example.countries.preasentation.countryDetail.comps.CountryScreen
import com.example.countries.preasentation.ui.theme.CountriesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = Screen.countriesScreen.route){
                        composable(
                            route = Screen.countriesScreen.route
                        ){
                            CountriesScreen(navController)
                        }

                        composable(
                            route = Screen.countryScreen.route + "/{countryName}"
                        ){
                            CountryScreen()
                        }
                    }
                }
            }
        }
    }
}