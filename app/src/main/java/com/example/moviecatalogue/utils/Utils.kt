package com.example.moviecatalogue.utils

import android.content.Context
import com.example.moviecatalogue.R
import kotlin.random.Random

object Utils {
    fun randomColor(context: Context): Int{
        val colors = context.resources.getIntArray(R.array.stroke_colors)
        val randomColor = colors[Random.nextInt(colors.size)]
        return randomColor
    }
}