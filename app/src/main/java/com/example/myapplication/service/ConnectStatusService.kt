package com.example.myapplication.service

import com.example.myapplication.model.ConnectRaspberryPi
import javax.inject.Inject
import javax.inject.Singleton

interface ConnectStatusService {

    fun getConnectList(): List<ConnectRaspberryPi>

    fun addConnect(connectRaspberryPi: ConnectRaspberryPi)

    fun removeConnect(connectRaspberryPi: ConnectRaspberryPi)

}

@Singleton
class FakeConnectStatusServiceImp @Inject constructor() : ConnectStatusService {

    private val connectList =
        mutableListOf(ConnectRaspberryPi("Ashley"), ConnectRaspberryPi("Jess"))

    private val raspberryPi =
        mutableListOf(ConnectRaspberryPi("room"), ConnectRaspberryPi("kitchen"))

    override fun getConnectList(): List<ConnectRaspberryPi> {
        return connectList
    }

    override fun addConnect(connectRaspberryPi: ConnectRaspberryPi) {
        connectList += connectRaspberryPi
    }

    override fun removeConnect(connectRaspberryPi: ConnectRaspberryPi) {
        connectList -= connectRaspberryPi
    }


    fun getAllRaspberryPiNames(): List<String> {
        return connectList.map {
            it.name
        }
    }

    fun getRaspberryPiName(index: Int): String {
        if (index < 0 || connectList.size <= index) {
            throw IndexOutOfBoundsException()
        }
        return connectList[index].name
    }
}
