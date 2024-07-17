package com.example.sui.feature.vm

import androidx.lifecycle.*
import com.example.sui.feature.domain.use_case.GetAllSui

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FullGenealogyViewModel @Inject constructor(
    private val getAllSui: GetAllSui
) : ViewModel() {



}
