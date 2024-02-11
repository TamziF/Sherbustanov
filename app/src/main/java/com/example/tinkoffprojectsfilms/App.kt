package com.example.tinkoffprojectsfilms

import android.app.Application
import android.content.Context
import com.example.tinkoffprojectsfilms.ioc.ApplicationComponent

class App: Application() {
    val applicationComponent by lazy { ApplicationComponent() }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}