package com.seiki.android.wallpaperapptest.screens.main_fragment

import com.seiki.android.wallpaperapptest.model.Buttons

class ButtonsPattern {
    fun add(): MutableList<Buttons> {
        val list = mutableListOf<Buttons>()

        list.add(Buttons(1, "природа", "nature+vertical"))
        list.add(Buttons(2, "еда", "food+vertical"))
        list.add(Buttons(3, "животные", "animals+vertical"))
        list.add(Buttons(4, "люди", "people+vertical"))
        list.add(Buttons(5, "стиль", "fashion+vertical"))
        list.add(Buttons(6, "религия", "religion+vertical"))
        list.add(Buttons(7, "здоровье", "health+vertical"))
        list.add(Buttons(8, "здания", "buildings+vertical"))

        return list
    }
}