package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.ConnectRaspberryPi
import com.example.myapplication.service.ConnectStatusService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectStatusViewModel @Inject constructor(private val connectStatusService: ConnectStatusService): ViewModel() {
    private val _connectList = MutableLiveData<List<ConnectRaspberryPi>>(emptyList())

    val connectList: LiveData<List<ConnectRaspberryPi>>
        get() = _connectList

    fun connectStatus(){
        _connectList.value = connectStatusService.getConnectList()
    }
}