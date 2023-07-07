package com.example.myapplication.model.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TokenInfo.TABLE_NAME)
data class TokenInfo(
    @PrimaryKey
    val id : Int,

    @ColumnInfo(name = "token")
    val token: String

){
    companion object{
        const val TABLE_NAME = "token"
    }
}
