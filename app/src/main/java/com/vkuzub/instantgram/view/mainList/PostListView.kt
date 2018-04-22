package com.vkuzub.instantgram.view.mainList

import com.vkuzub.instantgram.base.view.HideShowContentView
import com.vkuzub.instantgram.data.network.models.response.Post

interface PostListView: HideShowContentView {

    fun fillList(list: List<Post>)

}