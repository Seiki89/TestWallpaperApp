package com.seiki.android.wallpaperapptest.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.seiki.android.wallpaperapptest.APP
import com.seiki.android.wallpaperapptest.R
import com.seiki.android.wallpaperapptest.databinding.ActivityAppBinding


class AppActivity : AppCompatActivity() {
    private lateinit var bind:ActivityAppBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityAppBinding.inflate(layoutInflater)
        setContentView(bind.root)

        APP = this
        navController = Navigation.findNavController(this, R.id.fragmentContainer)

    }



}