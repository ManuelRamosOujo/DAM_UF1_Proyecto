package com.example.uf1_proyecto

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class EventDAO {
    val baseURL = "https://connect-api.veetal.app"
    var urlString = "\"$baseURL?city=Pontevedra&distance=null&local=false&from_date=2023-01-01&to_date=2023-12-31"

    fun getEvent(){
        try {
            val url = URL(urlString)

            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            connection.setRequestProperty("Accept", "application/json")

            val inputStream = connection.inputStream
            val reader = BufferedReader(InputStreamReader(inputStream))
            val response = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            println(response.toString())

            reader.close()
            connection.disconnect()

        } catch (e: Exception) {
            println("Excepción: ${e.message}")
        }
    }
}