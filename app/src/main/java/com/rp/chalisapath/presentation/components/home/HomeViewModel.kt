package com.rp.chalisapath.presentation.components.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rp.chalisapath.domain.model.ChalishaListItem
import com.rp.chalisapath.domain.usecase.GetChalishaInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val getChalishaInfoUseCase: GetChalishaInfoUseCase) :ViewModel() {

    private val _chalishaList = MutableStateFlow<List<ChalishaListItem>>(emptyList())
    val chalisaList:StateFlow<List<ChalishaListItem>> get() = _chalishaList

    fun loadChalishaInfo(){
        viewModelScope.launch {
            _chalishaList.value =getChalishaInfoUseCase.invoke()
        }
    }

}