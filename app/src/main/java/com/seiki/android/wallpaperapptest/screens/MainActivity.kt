package com.seiki.android.wallpaperapptest.screens

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.seiki.android.wallpaperapptest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private var progressStatus = 0
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        animateTitle()
        progressBarEmu()

    }

    private fun progressBarEmu() {
        //эмулируем прогресс загрузки прогрессбара
        CoroutineScope(Dispatchers.Main).launch {
            while (progressStatus < 100) {
                progressStatus += 1
                delay(10)
                handler.post {
                    bind.progressBar.progress = progressStatus
                    bind.txtProgress.text = "$progressStatus"
                }
            }
        }
    }

    private fun animateTitle() {
        //анимируем иконку
        //по окончании анимации переходим на основное активити
        //закрываем текущее активити
        bind.imgTitle.animate().apply {
            duration = 0
            alpha(0f)
        }.withEndAction {
            bind.imgTitle.animate().apply {
                duration = 1000
                alpha(1f)
            }.withEndAction {
                val intent = Intent(this, AppActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

}