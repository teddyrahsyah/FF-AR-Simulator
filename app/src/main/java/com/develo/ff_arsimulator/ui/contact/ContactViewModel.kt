package com.develo.ff_arsimulator.ui.contact

import androidx.lifecycle.ViewModel
import com.develo.ff_arsimulator.data.source.DataRepository

class ContactViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun postMessage(sender: String, message: String) = dataRepository.addMessagge(sender, message)
}