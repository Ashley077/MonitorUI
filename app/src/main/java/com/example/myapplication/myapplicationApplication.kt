package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.model.data.local.database.UserDataBase


class myapplicationApplication : Application(){
    lateinit var db : UserDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this,UserDataBase::class.java,"userDB").build()
    }
}