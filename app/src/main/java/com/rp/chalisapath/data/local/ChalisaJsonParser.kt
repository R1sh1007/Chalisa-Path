package com.rp.chalisapath.data.local

import android.content.Context
import android.util.Log
import com.rp.chalisapath.ChalishaPathApp
import com.rp.chalisapath.domain.model.ChalishaDetails
import com.rp.chalisapath.domain.model.ChalishaListItem
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class ChalisaJsonParser @Inject constructor(private val context :Context):ChalisaDataSource {

    override fun getChalisaInfo(): List<ChalishaListItem> {
        var jsonString=""
        try {
             jsonString = context.assets.open("chalisa_list.json").bufferedReader().use { it.readText() }
            Log.d("ChalisaJsonParser", "JSON Input: $jsonString")
        }catch (e:Exception){
            Log.e("CHE","Error in parsingChalisaInfo==============${e.stackTrace}")
        }
        return Json.decodeFromString(jsonString)
    }

    override fun getChalisaDetails(fileName: String): List<ChalishaDetails> {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }

}
