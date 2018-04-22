package com.vkuzub.instantgram.data

import com.vkuzub.instantgram.data.db.DbHelper
import com.vkuzub.instantgram.data.network.FakeApiHelper
import com.vkuzub.instantgram.data.prefs.PreferencesHelper

interface DataManager : FakeApiHelper, PreferencesHelper, DbHelper