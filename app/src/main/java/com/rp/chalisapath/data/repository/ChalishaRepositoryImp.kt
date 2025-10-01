package com.rp.chalisapath.data.repository

import com.rp.chalisapath.data.local.ChalisaDataSource
import com.rp.chalisapath.data.local.ChalisaJsonParser
import com.rp.chalisapath.domain.model.ChalishaDetails
import com.rp.chalisapath.domain.model.ChalishaListItem
import com.rp.chalisapath.domain.repository.ChalishaRepository
import javax.inject.Inject

class ChalishaRepositoryImp @Inject constructor(private val chalishaDataSource: ChalisaDataSource) : ChalishaRepository{

    private val chalishaInfo by lazy { chalishaDataSource.getChalisaInfo() }

    override suspend fun getChalisaInfos(): List<ChalishaListItem> =chalishaInfo

    override suspend fun getChalisaById(id: String):List<ChalishaDetails> {
        return chalishaDataSource.getChalisaDetails(id)
    }
}