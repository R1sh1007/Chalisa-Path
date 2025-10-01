package com.rp.chalisapath.presentation.components.chalisaDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rp.chalisapath.domain.model.ChalishaDetails
import com.rp.chalisapath.domain.usecase.GetChalishaByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChalisaDetailsViewModel @Inject constructor(private val useCase: GetChalishaByIdUseCase):ViewModel() {

    private val _chalisaDetails= MutableStateFlow<List<ChalishaDetails>>(emptyList())
    val chalishaDetails :StateFlow<List<ChalishaDetails>> get() = _chalisaDetails

    fun getChalisaById(id:String){
        viewModelScope.launch {
            _chalisaDetails.value=useCase.invoke(id)
        }
    }

}