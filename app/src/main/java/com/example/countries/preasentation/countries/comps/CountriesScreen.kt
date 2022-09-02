package com.example.countries.preasentation.countries.comps

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.countries.preasentation.Screen
import com.example.countries.preasentation.countries.CountriesState
import com.example.countries.preasentation.countries.CountriesViewModel
import com.example.countries.preasentation.countryDetail.comps.ErrorView

@Composable
fun CountriesScreen(
    navControlller: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    val stateFlow = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = stateFlow.value) {
            is CountriesState.Error -> ErrorView(state.error)
            CountriesState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is CountriesState.Success -> LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryRow(country = country, onClick = {
                        navControlller.navigate(Screen.countryScreen.route + "/${country.cca2}")
                    })
                }
            }
        }


    }

}