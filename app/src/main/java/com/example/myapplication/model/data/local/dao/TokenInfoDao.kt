package com.example.myapplication.model.data.local.dao

import androidx.room.*
import com.example.myapplication.model.data.local.entity.TokenInfo

@Dao
interface TokenInfoDao{
    @Query("SELECT * FROM ${TokenInfo.TABLE_NAME} LIMIT 1")
    suspend fun getToken() : TokenInfo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfo: TokenInfo) : Long

    @Delete
    suspend fun delete(userInfo: TokenInfo): Int
}