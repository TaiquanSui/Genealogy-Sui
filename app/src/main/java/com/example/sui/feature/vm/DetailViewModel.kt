package com.example.sui.feature.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sui.feature.domain.model.Sui
import com.example.sui.feature.domain.use_case.DetailUseCases
import com.example.sui.feature.ui.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val detailUseCases: DetailUseCases
) : ViewModel() {

    var state = mutableStateOf(DetailState())
        private set


    fun getInfoById(id: String) {
        viewModelScope.launch {
            val selected = detailUseCases.getPersonById(id) ?: return@launch
            val father = detailUseCases.getFather(id)
            val mother: Sui? =
            if(father==null){
                null
            }else{
                if(selected.idMother.isBlank()){
                    detailUseCases.getSpouse(father.id).firstOrNull()?.firstOrNull()
                } else {
                    detailUseCases.getMother(id)
                }
            }


            val spouse = detailUseCases.getSpouse(id).firstOrNull()
            val children = detailUseCases.getChildren(id).firstOrNull()

            state.value = state.value.copy(selected = selected, children = children, spouse = spouse, father = father, mother = mother)
            Log.println(Log.DEBUG,"personal info",state.value.toString())
        }
    }


}