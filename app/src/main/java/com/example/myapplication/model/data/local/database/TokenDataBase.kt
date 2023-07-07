package com.example.myapplication.model.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.model.data.local.entity.TokenInfo

@Database( entities = [TokenInfo::class], version = 1)
abstract class TokenDataBase : RoomDatabase(){
    abstract val tokenInfoDao : TokenInfoDao
}