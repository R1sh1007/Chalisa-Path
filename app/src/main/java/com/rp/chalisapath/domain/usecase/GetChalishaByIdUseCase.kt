package com.rp.chalisapath.domain.usecase

import com.rp.chalisapath.domain.model.ChalishaDetails
import com.rp.chalisapath.domain.repository.ChalishaRepository
import javax.inject.Inject

class GetChalishaByIdUseCase @Inject constructor(private val repository: ChalishaRepository) {
    suspend operator fun invoke(id :String): List<ChalishaDetails> {
        return repository.getChalisaById(id)
    }


}