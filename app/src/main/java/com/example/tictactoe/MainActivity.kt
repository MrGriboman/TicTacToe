package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.tictactoe.Constants.ENGLISH
import com.example.tictactoe.Constants.RUSSIAN
import com.example.tictactoe.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var currentLanguage = RUSSIAN
    lateinit var mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mp = MediaPlayer.create(this, R.raw.btn_sound)
        mp.setOnCompletionListener {
            mp.reset()
            mp.release()
        }

        binding.apply {
            btnSinglePlayer.setOnClickListener {
                mp = MediaPlayer.create(this@MainActivity, R.raw.btn_sound)
                mp.start()
                Intent(this@MainActivity, SinglePlayerActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnMultiPlayer.setOnClickListener {
                mp = MediaPlayer.create(this@MainActivity, R.raw.btn_sound)
                mp.start()
                Intent(this@MainActivity, MultiPlayerActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnLanguage.setOnClickListener {
                mp = MediaPlayer.create(this@MainActivity, R.raw.btn_sound)
                mp.start()
                btnLanguage.setImageResource(
                    if (currentLanguage == RUSSIAN) R.drawable.ic_english.also {
                        currentLanguage = ENGLISH
                        setLocale("en")
                    }
                    else R.drawable.ic_russian.also {
                        currentLanguage = RUSSIAN
                        setLocale("ru")
                    }
                )
                tvName.text = getString(R.string.tic_tac_toe)
            }
        }
    }

    private fun setLocale(language: String) {
        val resources = resources
        val metrics = resources.displayMetrics
        val configuration = resources.configuration
        configuration.setLocale(Locale(language))
        resources.updateConfiguration(configuration, metrics)

    }

}