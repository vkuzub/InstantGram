package com.vkuzub.instantgram.data.db

import com.vkuzub.instantgram.data.network.models.response.Post

interface DbHelper {

    fun savePosts(list: List<Post>?)

    fun loadPosts(): List<Post>?

    fun updatePostByUuid(uuid: String)

    fun removePostByUuid(uuid: String)
}