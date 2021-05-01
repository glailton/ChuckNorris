package br.com.guiabolso.chucknorris

import android.app.Application
import br.com.guiabolso.chucknorris.di.component.AppComponent
import br.com.guiabolso.chucknorris.di.component.DaggerAppComponent
import br.com.guiabolso.chucknorris.di.module.NetworkModule

class MainApplication: Application() {

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(this))
            .build()
    }

}