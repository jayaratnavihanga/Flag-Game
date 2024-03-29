package com.example.flaggametest

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


data class Country(val countryCode: String, val countryName: String)

fun loadCountriesFromAssets(context: Context): List<Country> {
    val countriesList = mutableListOf<Country>()
    try {
        val inputStream: InputStream = context.assets.open("countries.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val jsonString = String(buffer, Charset.defaultCharset())
        val jsonObject = JSONObject(jsonString)

        val keysIterator = jsonObject.keys()
        while (keysIterator.hasNext()) {
            val countryCode = keysIterator.next()
            val countryName = jsonObject.getString(countryCode)
            countriesList.add(Country(countryCode, countryName))
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return countriesList

}

