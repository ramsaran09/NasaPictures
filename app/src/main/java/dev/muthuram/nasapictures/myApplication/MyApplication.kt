package dev.muthuram.nasapictures.myApplication

import android.app.Application
import dev.muthuram.nasapictures.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(AppModule.appModules())
        }
    }
}