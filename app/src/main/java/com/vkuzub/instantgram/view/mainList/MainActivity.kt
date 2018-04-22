package com.vkuzub.instantgram.view.mainList

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.base.BaseMvpViewActivity
import com.vkuzub.instantgram.data.network.models.response.Post
import kotlinx.android.synthetic.main.activity_main_content.*
import timber.log.Timber

class MainActivity : BaseMvpViewActivity(), PostListView {

    @InjectPresenter
    lateinit var postListPresenter: PostListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getContentView(): View = contentView

    override fun fillList(list: List<Post>) {
    }
}