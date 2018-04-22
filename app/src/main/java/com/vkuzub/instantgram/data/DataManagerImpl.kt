package com.vkuzub.instantgram.data

import com.vkuzub.instantgram.data.db.DbHelper
import com.vkuzub.instantgram.data.network.FakeApiHelper
import com.vkuzub.instantgram.data.prefs.PreferencesHelper
import javax.inject.Inject

class DataManagerImpl @Inject constructor(val fakeApiHelper: FakeApiHelper,
                                          val preferencesHelper: PreferencesHelper,
                                          val dbHelper: DbHelper) : DataManager {

}