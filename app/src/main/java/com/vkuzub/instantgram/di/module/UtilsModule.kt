package com.vkuzub.instantgram.di.module

import android.content.Context
import com.vkuzub.instantgram.data.DataManager
import com.vkuzub.instantgram.data.DataManagerImpl
import com.vkuzub.instantgram.data.db.DbHelper
import com.vkuzub.instantgram.data.db.DbHelperImpl
import com.vkuzub.instantgram.data.network.FakeApiHelper
import com.vkuzub.instantgram.data.network.FakeApiHelperImpl
import com.vkuzub.instantgram.data.prefs.PreferencesHelper
import com.vkuzub.instantgram.data.prefs.PreferencesHelperImpl
import com.vkuzub.instantgram.utils.ToastUtils
import com.vkuzub.instantgram.utils.impl.ToastUtilsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideFakeApiHelper(context: Context): FakeApiHelper = FakeApiHelperImpl(context)

    @Singleton
    @Provides
    fun provideDbHelper(): DbHelper = DbHelperImpl()

    @Singleton
    @Provides
    fun providePreferencesHelper(context: Context): PreferencesHelper = PreferencesHelperImpl(context)

    @Singleton
    @Provides
    fun provideDataManager(fakeApiHelper: FakeApiHelper, preferencesHelper: PreferencesHelper,
                           dbHelper: DbHelper): DataManager = DataManagerImpl(fakeApiHelper, preferencesHelper, dbHelper)

    @Singleton
    @Provides
    fun provideToastUtils(context: Context): ToastUtils = ToastUtilsImpl(context)
}