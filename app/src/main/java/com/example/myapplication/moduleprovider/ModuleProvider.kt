package com.example.myapplication.moduleprovider

import android.content.Context
import androidx.room.Room
import com.example.myapplication.model.data.local.dao.TokenInfoDao
import com.example.myapplication.model.data.local.database.TokenDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleProvider {
    @Provides
    @Singleton
    fun dbProvider(@ApplicationContext context: Context): TokenDataBase {
        return Room.databaseBuilder(context, TokenDataBase::class.java, "userDB").build()
    }

    @Provides
    @Singleton
    fun tokenDAOProvider(tokenDataBase: TokenDataBase): TokenInfoDao {
        return tokenDataBase.tokenInfoDao
    }
}