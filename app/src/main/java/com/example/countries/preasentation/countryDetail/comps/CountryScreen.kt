package com.example.countries.preasentation.countryDetail.comps

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countries.domain.model.Country
import com.example.countries.preasentation.countries.comps.CountryInfo
import com.example.countries.preasentation.countryDetail.CountryDetailViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun CountryScreen(
    viewModel: CountryDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        state.country?.let { country ->
            LazyColumn(modifier = Modifier.fillMaxSize()){
                item{

                    Column(modifier = Modifier
                        .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceBetween) {

                        CountryMapView(country = country)

                        CountryDetailInfo(country)
                    }
                }
            }
        }
        state.error.isNotBlank().let {
            ErrorView(state.error)
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}



@Composable
fun CountryDetailInfo(country: Country){

    CountryInfo(country = country)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp) ,
        verticalArrangement = Arrangement.SpaceBetween) {
            country.languages?.spa?.let {
                Text(text = "Spoken language : " +country.languages.spa)
            }
            country.region?.let {
                Text(text = "Region : " +country.region)
            }
            country.subregion?.let {
                Text(text = "Subregion : " +country.subregion)
            }
    }
}

@Composable
fun ErrorView(msg : String) {
    Text(
        text = msg,
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun CountryMapView(country: Country){
    val countryLatLng = LatLng(country.latlng[0], country.latlng[1])
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(countryLatLng, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            position = countryLatLng,
            title = country.name?.official,
            snippet = "Marker in " + country.name?.official
        )
    }
}

