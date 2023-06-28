package com.example.myapplication.model.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.data.local.dao.UserInfoDao
import com.example.myapplication.model.data.local.entity.UserInfo

@Database( entities = [UserInfo::class], version = 1)
abstract class UserDataBase : RoomDatabase(){
    abstract val userInfoDao : UserInfoDao
}