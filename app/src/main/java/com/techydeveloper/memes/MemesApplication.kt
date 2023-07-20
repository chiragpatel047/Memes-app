package com.techydeveloper.memes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MemesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}