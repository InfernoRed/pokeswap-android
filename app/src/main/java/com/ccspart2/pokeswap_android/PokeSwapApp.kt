package com.ccspart2.pokeswap_android

import android.app.Application
import timber.log.Timber

class PokeSwapApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
