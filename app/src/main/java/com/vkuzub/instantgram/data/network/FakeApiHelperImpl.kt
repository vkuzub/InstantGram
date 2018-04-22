package com.vkuzub.instantgram.data.network

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.data.network.models.response.Post
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FakeApiHelperImpl @Inject constructor(val context: Context) : FakeApiHelper {

    override fun loadAllPosts(): Single<List<Post>> {
        val inputStream = context.resources.openRawResource(R.raw.posts)
        val json = inputStream.bufferedReader().use { it.readText() }
        val objectMapper = ObjectMapper().registerKotlinModule()
        val typeFactory = objectMapper.typeFactory
        val list: List<Post> = objectMapper.readValue(json, typeFactory.constructCollectionType(List::class.java, Post::class.java))
        return Single.just(list).delay(2, TimeUnit.SECONDS)
    }

}