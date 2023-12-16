package com.gurpreetsk.liveclock

import android.app.Application
import android.os.StrictMode

class LiveClockApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StrictMode.enableDefaults()
    }
}
