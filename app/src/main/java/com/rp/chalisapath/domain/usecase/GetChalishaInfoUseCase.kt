package com.rp.chalisapath.domain.usecase

import com.rp.chalisapath.domain.model.ChalishaListItem
import com.rp.chalisapath.domain.repository.ChalishaRepository
import javax.inject.Inject

class GetChalishaInfoUseCase @Inject constructor(private val repository: ChalishaRepository){
    suspend operator fun invoke():List<ChalishaListItem>{
        return repository.getChalisaInfos()
    }

}