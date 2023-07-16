package com.example.tuan2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.tuan2.databinding.ActivityRetrofitBinding
import com.example.tuan2.retrofit.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com"

class RetrofitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRetrofitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var counted = 0
        binding.btn.setOnClickListener{
            binding.counted.text = counted++.toString()
        }

        binding.buttonFetchUser.setOnClickListener {
            val userIdString = binding.editTextUserId.text.toString()
            if (TextUtils.isEmpty(userIdString)) {
                Toast.makeText(this, "Please enter user ID", Toast.LENGTH_SHORT).show()
            } else {
                val userId = userIdString.toInt()
                GlobalScope.launch(Dispatchers.IO) {
                    try {
                        val userResponse = api.getUserById(userId).awaitResponse()
                        delay(2000L)
                        if (userResponse.isSuccessful) {
                            val user = userResponse.body()
                            Log.d("nguoidung", user.toString())
                            runOnUiThread {
                                binding.textViewUserName.text = "${user?.name}"
                                binding.textViewUserEmail.text = "${user?.email}"
                                binding.textViewUserPhone.text = "${user?.phone}"
                            }
                        } else {
                            runOnUiThread {
                                Toast.makeText(this@RetrofitActivity, "User not available", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@RetrofitActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private val api: UserService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService::class.java)
    }
}