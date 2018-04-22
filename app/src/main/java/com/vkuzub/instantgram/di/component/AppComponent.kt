package com.vkuzub.instantgram.di.component

import com.vkuzub.instantgram.base.BaseMvpViewActivity
import com.vkuzub.instantgram.di.module.AppModule
import com.vkuzub.instantgram.di.module.UtilsModule
import com.vkuzub.instantgram.view.mainList.PostListPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, UtilsModule::class))
interface AppComponent {

    fun inject(activity: BaseMvpViewActivity)

    fun inject(presenter: PostListPresenter)
}