package com.example.myapplication.service

import com.example.myapplication.model.remote.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.POST

/**
 *  ApiService 提供登入、註冊
 *
 *  @author <a href='https://github.com/Ashley077'>Ashley</a>
 */
interface ApiService {

    /**
     *"/Login"的 HTTP POST 請求
     * login 需放入 coroutine 做使用
     * @param userInfo 包括使用者登入訊息
     *
     * @return [LoginResult]
     *
     * @author <a href='https://github.com/Ashley077'>Ashley</a>
     */
    @POST("/login")
    suspend fun login(@Body userInfo: UserInfo): Response<LoginResult>

    /**
     * "/Register"的 HTTP POST 請求
     * register 需放入 coroutine 做使用
     *
     * @param userRegisterInfo 使用者註冊資訊
     *
     * @return [RegisterResult]
     *
     * @author <a href='https://github.com/Ashley077'>Ashley</a>
     */
    @POST("/register")
    suspend fun register(@Body userRegisterInfo: UserRegisterInfo): Response<RegisterResult>

    /**
     * "/checkToken"的 HTTP POST 請求
     *
     * @param token 傳入需檢查的 Token
     *
     * @return String
     *
     * @author Ashley
     */
    @POST("/checkToken")
    suspend fun checkToken(@Body checkUserTokenInfo: CheckUserTokenInfo): Response<CheckUserTokenResult>
}