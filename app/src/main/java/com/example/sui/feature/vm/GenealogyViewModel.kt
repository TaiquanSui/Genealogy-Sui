package com.example.sui.feature.vm

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.sui.feature.domain.use_case.GenealogyUseCases
import com.example.sui.feature.ui.GenealogyListState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenealogyViewModel @Inject constructor(
    private val genealogyUseCases: GenealogyUseCases
) : ViewModel() {

    var state = mutableStateOf(GenealogyListState())
        private set

    var bottomSheetState = mutableStateOf(false)
        private set


    init {
        getGenealogyList()
    }

    fun getGenealogyList(){
        viewModelScope.launch {
            val name = state.value.nameFilter
            val generation = state.value.generationFilter

            val filteredList = if (name != null && generation != null) {
                genealogyUseCases.filterGenealogyByNameAndGen("%$name%", generation).firstOrNull() ?: emptyList()
            } else if (name != null) {
                genealogyUseCases.filterGenealogyByFirstName("%$name%").firstOrNull() ?: emptyList()
            } else if (generation != null) {
                genealogyUseCases.filterGenealogyByGeneration(generation).firstOrNull() ?: emptyList()
            } else {
                genealogyUseCases.getFullGenealogy().firstOrNull() ?: emptyList()
            }
            state.value = state.value.copy(genealogyList = filteredList)
        }
    }

    fun nameChanged(name:String?){
        viewModelScope.launch {
            state.value = state.value.copy(nameFilter = name)
        }
    }

    fun generationChanged(generation: Int?){
        viewModelScope.launch {
            state.value = state.value.copy(generationFilter = generation)
        }
    }

    fun bottomSheet(){
        viewModelScope.launch {
            bottomSheetState.value = !bottomSheetState.value
        }
    }


}
