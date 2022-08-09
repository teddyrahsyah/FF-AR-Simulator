package com.develo.ff_arsimulator.ui.modules

import androidx.lifecycle.ViewModel
import com.develo.ff_arsimulator.data.source.DataRepository

class ModuleViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getModules() = dataRepository.getModules()
    fun getTheoryDetail(id: String) = dataRepository.getTheoryDetail(id)
}