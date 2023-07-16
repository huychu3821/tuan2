package com.example.tuan2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import com.example.tuan2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var permissionLauncher : ActivityResultLauncher<Array<String>>
    private var isSmsReceiverPermission = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

       mainBinding.broadcastReceiver.setOnClickListener{
            val broadcastReceiverIntent = Intent(this@MainActivity, BroadcastReceiverActivity::class.java)
            startActivity(broadcastReceiverIntent)
        }

        mainBinding.retrofit.setOnClickListener{
            val retrofitIntent = Intent(this@MainActivity, RetrofitActivity::class.java)
            startActivity(retrofitIntent)
        }
    }
}