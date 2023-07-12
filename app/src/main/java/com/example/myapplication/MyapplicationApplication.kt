package com.example.myapplication

import android.app.Application
import androidx.room.Room
import com.example.myapplication.model.data.local.database.TokenDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyapplicationApplication : Application()