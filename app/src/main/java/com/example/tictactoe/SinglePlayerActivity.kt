package com.example.tictactoe

import android.graphics.PorterDuff
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.example.tictactoe.databinding.ActivitySinglePlayerBinding
import com.example.tictactoe.Constants.CROSS
import com.example.tictactoe.Constants.CIRCLE
import kotlinx.coroutines.delay

class SinglePlayerActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivitySinglePlayerBinding
    private var btns: MutableList<Int> = MutableList(9) {0}
    private lateinit var btnsViews: MutableList<ImageButton>
    private var turn = 1
    private var currentFigure = CROSS
    private var gameEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySinglePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnsViews = mutableListOf(btnFirst, btnSecond, btnThird, btnForth, btnFifth, btnSixth,
            btnSeventh, btnEighth, btnNinth)

            tvTurn.text = getString(R.string.single_player)
            btnFirst.setOnClickListener(this@SinglePlayerActivity)
            btnSecond.setOnClickListener(this@SinglePlayerActivity)
            btnThird.setOnClickListener(this@SinglePlayerActivity)
            btnForth.setOnClickListener(this@SinglePlayerActivity)
            btnFifth.setOnClickListener(this@SinglePlayerActivity)
            btnSixth.setOnClickListener(this@SinglePlayerActivity)
            btnSeventh.setOnClickListener(this@SinglePlayerActivity)
            btnEighth.setOnClickListener(this@SinglePlayerActivity)
            btnNinth.setOnClickListener(this@SinglePlayerActivity)

            btnRestart.setOnClickListener {
                replay()
            }
        }
    }

    override fun onClick(v: View?) {
        val btn = v as ImageButton
        if (btn.drawable != null || gameEnded)
            return
        btn.setImageResource(R.drawable.ic_cross)
        btn.setColorFilter(
            ContextCompat.getColor(this, R.color.green),
            PorterDuff.Mode.SRC_IN)
        changeButtonState(btn)

        if (isWin() || isDraw()) {
            gameEnded = true
            binding.tvTurn.text =
                if (isWin()) getString(R.string.bot_beaten)
                else getString(R.string.draw)
            binding.btnRestart.visibility = View.VISIBLE
            return
        }

        Thread.sleep(100)
        bot()
        if (isWin() || isDraw()) {
            gameEnded = true
            binding.tvTurn.text =
                if (isWin()) getString(R.string.bot_wins)
                else getString(R.string.draw)
            binding.btnRestart.visibility = View.VISIBLE
            return
        }
    }

    private fun changeButtonState(btn: ImageButton) {
        when(btn.id) {
            R.id.btnFirst -> btns[0] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnSecond -> btns[1] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnThird -> btns[2] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnForth -> btns[3] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnFifth -> btns[4] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnSixth -> btns[5] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnSeventh -> btns[6] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnEighth -> btns[7] = if (currentFigure == CROSS) CROSS else CIRCLE
            R.id.btnNinth -> btns[8] = if (currentFigure == CROSS) CROSS else CIRCLE
        }
    }

    private fun isWin(): Boolean {
        if (btns[0] == btns[1] && btns[0] == btns[2] && btns[0] != 0)
            return true
        if (btns[3] == btns[4] && btns[3] == btns[5] && btns[3] != 0)
            return true
        if (btns[6] == btns[7] && btns[6] == btns[8] && btns[6] != 0)
            return true
        if (btns[0] == btns[3] && btns[0] == btns[6] && btns[0] != 0)
            return true
        if (btns[1] == btns[4] && btns[1] == btns[7] && btns[1] != 0)
            return true
        if (btns[2] == btns[5] && btns[2] == btns[8] && btns[2] != 0)
            return true
        if (btns[0] == btns[4] && btns[0] == btns[8] && btns[0] != 0)
            return true
        if (btns[2] == btns[4] && btns[2] == btns[6] && btns[2] != 0)
            return true
        return false
    }

    private fun isDraw(): Boolean {
        if (!isWin() && !btns.any { it == 0 })
            return true
        return false
    }

    private fun bot() {
        for (i in btns.indices) {
            if (btns[i] == 0) {
                btns[i] = 2
                if (isWin()) {
                    botTurn(i)
                    return
                }
                btns[i] = 0
            }
        }
        for (i in btns.indices) {
            if (btns[i] == 0) {
                btns[i] = 1
                if (isWin()) {
                    botTurn(i)
                    return
                }
                btns[i] = 0
            }
        }

        if (btns[4] == 0) {
            botTurn(4)
            return
        }

        for (i in btns.indices) {
            if (btns[i] == 0) {
                botTurn(i)
                return
            }
        }
    }

    private fun indexToBtn(index: Int): ImageButton? = when(index) {
        0 -> binding.btnFirst
        1 -> binding.btnSecond
        2 -> binding.btnThird
        3 -> binding.btnForth
        4 -> binding.btnFifth
        5 -> binding.btnSixth
        6 -> binding.btnSeventh
        7 -> binding.btnEighth
        8 -> binding.btnNinth
        else -> null
    }

    private fun botTurn(index: Int) {
        val btnView = indexToBtn(index) as ImageButton
        btnView.setImageResource(R.drawable.ic_circle)
        btnView.setColorFilter(
            ContextCompat.getColor(this, R.color.red),
            PorterDuff.Mode.SRC_IN)
        btns[index] = CIRCLE
    }

    private fun replay() {
        btns = MutableList(9) {0}
        for (btn in btnsViews) {
            btn.setImageResource(0)
        }
        binding.tvTurn.text = getString(R.string.single_player)
        binding.btnRestart.visibility = View.INVISIBLE
        gameEnded = false
    }
}