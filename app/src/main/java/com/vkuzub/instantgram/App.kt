package com.vkuzub.instantgram

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.vkuzub.instantgram.di.component.AppComponent
import com.vkuzub.instantgram.di.component.DaggerAppComponent
import com.vkuzub.instantgram.di.module.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {

    private lateinit var application: Application

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        application = this
        appComponent = buildAppComponent()

        initRealm()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().compactOnLaunch().schemaVersion(1).build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    private fun buildAppComponent() = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}