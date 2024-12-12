package com.inforcap.apphousy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforcap.apphousy.data.MansionRepository
import com.inforcap.apphousy.model.Mansion
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MansionViewModel: ViewModel() {

    private val repository: MansionRepository = MansionRepository()

    suspend fun getMansionList(): StateFlow<List<Mansion>> = repository.getMansionList().stateIn(viewModelScope)

    suspend fun getMansionById(id: Int): StateFlow<Mansion?> = repository.getMansionById(id).stateIn(viewModelScope)
}