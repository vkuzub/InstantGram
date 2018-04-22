package com.vkuzub.instantgram.di.module

import android.content.Context
import com.vkuzub.instantgram.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun getContext(): Context = app

}