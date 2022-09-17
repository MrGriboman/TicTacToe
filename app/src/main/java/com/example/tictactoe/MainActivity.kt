package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSinglePlayer.setOnClickListener {
                Intent(this@MainActivity, SinglePlayerActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnMultiPlayer.setOnClickListener {
                Intent(this@MainActivity, MultiPlayerActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}