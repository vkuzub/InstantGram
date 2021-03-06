package com.vkuzub.instantgram.data

import com.vkuzub.instantgram.data.db.DbHelper
import com.vkuzub.instantgram.data.network.FakeApiHelper
import com.vkuzub.instantgram.data.network.models.response.Post
import com.vkuzub.instantgram.data.prefs.PreferencesHelper
import javax.inject.Inject

class DataManagerImpl @Inject constructor(val fakeApiHelper: FakeApiHelper,
                                          val preferencesHelper: PreferencesHelper,
                                          val dbHelper: DbHelper) : DataManager {

    override fun loadAllPosts() = fakeApiHelper.loadAllPosts()

    override fun savePosts(list: List<Post>?) {
        dbHelper.savePosts(list)
    }

    override fun loadPosts() = dbHelper.loadPosts()

    override fun updatePostByUuid(uuid: String) {
        dbHelper.updatePostByUuid(uuid)
    }

    override fun removePostByUuid(uuid: String) {
        dbHelper.removePostByUuid(uuid)
    }
}