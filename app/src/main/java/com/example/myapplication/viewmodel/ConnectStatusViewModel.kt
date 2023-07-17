package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.service.ConnectStatusService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectStatusViewModel @Inject constructor(private val connectStatusService: ConnectStatusService): ViewModel() {
    private val _connectList = MutableLiveData<List<String>>(emptyList())

    val connectList: LiveData<List<String>>
        get() = _connectList

    fun connectStatus(){
        _connectList.value = connectStatusService.getConnectList()
    }
}