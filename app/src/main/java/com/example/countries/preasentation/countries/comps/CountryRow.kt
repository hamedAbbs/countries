package com.example.countries.preasentation.countries.comps

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.countries.domain.model.Country

@Composable
fun CountryRow(
    country: Country,
    onClick : (Country) -> Unit
) {
    Row(modifier = Modifier.clickable { onClick(country) }
        , verticalAlignment = Alignment.CenterVertically){
        CountryInfo(country = country)
    }

}

@Composable
fun CountryInfo(country: Country){

    country.flags?.let {
        Image(
            painter = rememberAsyncImagePainter(country.flags.png),
            contentDescription = null,
            modifier = Modifier.size(128.dp).padding(20.dp)
        )
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp) ,
            verticalArrangement = Arrangement.SpaceBetween) {
            country.name?.let { Text(text = country.name.official) }
            country.capital?.let{Text(text = "Capital : " + country.capital[0])}
            Text(text = "Population : ${country.population}")
        }
}