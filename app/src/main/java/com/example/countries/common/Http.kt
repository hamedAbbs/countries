package com.example.countries.common

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


 suspend fun getStringFromURL(urlString: String?): String {
    return withContext(Dispatchers.IO){
         var urlConnection: HttpURLConnection? = null
         val url = URL(urlString)
         urlConnection = url.openConnection() as HttpURLConnection
         urlConnection.requestMethod = "GET"
         urlConnection.readTimeout = 10000
         urlConnection.connectTimeout = 15000
         urlConnection.doOutput = true
         urlConnection.connect()
        BufferedReader(InputStreamReader(url.openStream())).use{br->
            val sb = StringBuilder()
            var line: String?=null
            while (br.readLine()?.also { line = it } != null) {
                sb.append(line!!.trimIndent())
            }
            sb.toString()
        }

     }
}