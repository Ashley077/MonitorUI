package com.example.myapplication.model.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserInfo.TABLE_NAME)
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "password")
    val password : String,
){
    companion object{
        const val TABLE_NAME = "user"
    }
}
