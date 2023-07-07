package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.model.data.local.database.TokenDataBase


class MyapplicationApplication : Application(){
    lateinit var db: TokenDataBase
    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, TokenDataBase::class.java, "token.sql").build()
    }
}