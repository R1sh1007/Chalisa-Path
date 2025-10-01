package com.rp.chalisapath.data.local

import com.rp.chalisapath.domain.model.ChalishaDetails
import com.rp.chalisapath.domain.model.ChalishaListItem

interface ChalisaDataSource {
    fun getChalisaInfo(): List<ChalishaListItem>
    fun getChalisaDetails(id: String): List<ChalishaDetails>
}