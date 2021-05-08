package com.example.tvproject.presentation

import android.app.Application
import android.content.Context

class TVShowApplication : Application() {

    companion object{
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}