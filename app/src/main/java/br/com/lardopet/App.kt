package br.com.lardopet

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtil.init(this)
    }
}