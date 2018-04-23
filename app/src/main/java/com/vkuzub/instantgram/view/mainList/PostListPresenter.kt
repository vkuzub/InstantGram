package com.vkuzub.instantgram.view.mainList

import com.arellomobile.mvp.InjectViewState
import com.vkuzub.instantgram.App
import com.vkuzub.instantgram.base.BasePresenter
import com.vkuzub.instantgram.data.DataManager
import com.vkuzub.instantgram.data.network.models.response.Post
import com.vkuzub.instantgram.utils.applySchedulers
import javax.inject.Inject

@InjectViewState
class PostListPresenter : BasePresenter<PostListView>() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var dataManager: DataManager

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        viewState.showLoading()

        rxAddSubscription(
                dataManager
                        .loadAllPosts()
                        .applySchedulers()
                        .subscribe({ onLoadDataSuccess(it) }, { onLoadDataFail(it) })
        )
    }

    private fun onLoadDataSuccess(list: List<Post>) {
        if (list.isNotEmpty()) {
            viewState.fillList(list)
            dataManager.savePosts(list)

            viewState.showContent()
        } else {
            viewState.showEmpty()
        }
    }

    private fun onLoadDataFail(throwable: Throwable) {
        viewState.showError()
        logException(throwable)
    }
}