package com.example.tictactoe

import android.graphics.Color.red
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.example.tictactoe.Constants.CROSS
import com.example.tictactoe.Constants.CIRCLE
import com.example.tictactoe.Constants.MODE
import com.example.tictactoe.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityGameBinding
    private var btns: MutableList<Int> = MutableList(9) {0}
    private var turn = 1
    private var currentFigure = CROSS
    private var gameEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvTurn.text = getString(R.string.turn, turn)
            btnFirst.setOnClickListener(this@GameActivity)
            btnSecond.setOnClickListener(this@GameActivity)
            btnThird.setOnClickListener(this@GameActivity)
            btnForth.setOnClickListener(this@GameActivity)
            btnFifth.setOnClickListener(this@GameActivity)
            btnSixth.setOnClickListener(this@GameActivity)
            btnSeventh.setOnClickListener(this@GameActivity)
            btnEighth.setOnClickListener(this@GameActivity)
            btnNinth.setOnClickListener(this@GameActivity)

            btnRestart.setOnClickListener {
                recreate()
            }
        }
    }

    override fun onClick(v: View?) {
        val btn = v as ImageButton
        if (btn.drawable != null || gameEnded)
            return
        val figureToSet = if (currentFigure == CROSS) R.drawable.ic_cross else R.drawable.ic_circle
        btn.setImageResource(figureToSet)
        btn.setColorFilter(
            ContextCompat.getColor(this, if (currentFigure == CROSS) R.color.green else R.color.red),
            PorterDuff.Mode.SRC_IN)
        changeButtonState(btn)
        currentFigure = if (currentFigure == CROSS) CIRCLE else CROSS

        if (isWin() || isDraw()) {
            gameEnded = true
            binding.tvTurn.text =
                if (isWin()) getString(R.string.win, turn)
                else getString(R.string.draw)
            binding.btnRestart.visibility = View.VISIBLE
            return
        }

        turn = if (turn == 1) 2 else 1
        binding.tvTurn.text = getString(R.string.turn, turn)
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
}