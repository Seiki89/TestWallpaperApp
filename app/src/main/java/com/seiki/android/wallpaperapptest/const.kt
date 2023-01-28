package com.seiki.android.wallpaperapptest

import com.seiki.android.wallpaperapptest.screens.AppActivity

lateinit var APP: AppActivity
fun nav(value:Int) = APP.navController.navigate(value)