package com.example.tuan2.retrofit

import com.example.tuan2.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>
}