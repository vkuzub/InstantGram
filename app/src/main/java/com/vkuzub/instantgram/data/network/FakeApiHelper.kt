package com.vkuzub.instantgram.data.network

import com.vkuzub.instantgram.data.network.models.response.Post
import io.reactivex.Single


interface FakeApiHelper {

    fun loadAllPosts(): Single<List<Post>>

}