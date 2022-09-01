package com.example.tictactoe

import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.tictactoe.Constants.MODE
import com.example.tictactoe.Constants.MULTI_PLAYER_MODE
import com.example.tictactoe.Constants.SINGLE_PLAYER_MODE
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val comingSoonToast: Toast = Toast.makeText(
            this@MainActivity,
            "Coming soon!",
            Toast.LENGTH_SHORT)

        binding.apply {
            btnSinglePlayer.setOnClickListener {
                /*Intent(this@MainActivity, GameActivity::class.java).also {
                    it.putExtra(MODE, SINGLE_PLAYER_MODE)
                    startActivity(it)
                }*/
                comingSoonToast.show()
            }

            btnMultiPlayer.setOnClickListener {
                Intent(this@MainActivity, GameActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}