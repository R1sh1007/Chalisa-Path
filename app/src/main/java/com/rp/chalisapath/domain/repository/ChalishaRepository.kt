package com.rp.chalisapath.domain.repository

import com.rp.chalisapath.domain.model.ChalishaListItem
import com.rp.chalisapath.domain.model.ChalishaDetails

interface ChalishaRepository {

    suspend fun getChalisaInfos(): List<ChalishaListItem>
    suspend fun getChalisaById(id: String): List<ChalishaDetails>
}