package com.vkuzub.instantgram.view.mainList

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.base.BaseMvpViewActivity
import com.vkuzub.instantgram.data.network.models.response.Post
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : BaseMvpViewActivity(), PostListView {

    @InjectPresenter
    lateinit var postListPresenter: PostListPresenter

    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initUi() {
        super.initUi()

        val layoutManager = LinearLayoutManager(this)
        val divider = DividerItemDecoration(this, layoutManager.orientation)
        adapter = PostsAdapter()
        rvData.layoutManager = layoutManager
        rvData.addItemDecoration(divider)
        rvData.adapter = adapter
    }

    override fun getContentView(): View = contentView

    override fun fillList(list: List<Post>) {
        adapter.data = list
    }
}