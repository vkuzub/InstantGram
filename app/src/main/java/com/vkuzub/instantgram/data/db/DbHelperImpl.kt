package com.vkuzub.instantgram.data.db

import com.vkuzub.instantgram.data.network.models.response.Post
import io.realm.Realm

class DbHelperImpl : DbHelper {

    override fun savePosts(list: List<Post>?) {
        if (list?.isNotEmpty()!!) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.insertOrUpdate(list)
            realm.commitTransaction()
            realm.close()
        }
    }

    override fun loadPosts(): List<Post>? {
        val realm = Realm.getDefaultInstance()
        val managedList = realm.where(Post::class.java).findAll()
        val unManagedList = realm.copyFromRealm(managedList)
        realm.close()
        return unManagedList
    }

    override fun removePostByUuid(uuid: String) {
        TODO("not implemented")
    }

    override fun updatePostByUuid(uuid: String) {
        TODO("not implemented")
    }
}