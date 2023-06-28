package com.example.myapplication.model.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.data.local.entity.UserInfo

@Dao
interface UserInfoDao{
    @Query("SELECT * FROM ${UserInfo.TABLE_NAME}")
    fun getAllUser() : LiveData<List<UserInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfo: UserInfo) : Long

    @Update
    suspend fun update(userInfo: UserInfo) : Int

    @Delete
    suspend fun delete(userInfo: UserInfo) : Int
}