package de.awolf.trip_compose

import android.app.Application
import de.awolf.trip_compose.di.AppModule
import de.awolf.trip_compose.di.AppModuleImpl

class MyApp: Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}