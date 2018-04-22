package com.vkuzub.instantgram.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vkuzub.instantgram.App
import com.vkuzub.instantgram.R
import com.vkuzub.instantgram.base.view.HideShowContentInteractionView
import com.vkuzub.instantgram.base.view.HideShowContentView
import com.vkuzub.instantgram.utils.ToastUtils
import com.vkuzub.instantgram.utils.extensions.gone
import com.vkuzub.instantgram.utils.extensions.visible
import icepick.Icepick
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.view_empty.*
import kotlinx.android.synthetic.main.view_error.*
import kotlinx.android.synthetic.main.view_loading.*
import javax.inject.Inject

abstract class BaseMvpViewActivity : MvpAppCompatActivity(), HideShowContentView,
        HideShowContentInteractionView, RxSupport, IcepickSupport {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var toastUtils: ToastUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        compositeDisposable = CompositeDisposable()
        initProgressDialog()
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        initUi()
        initRxSubscriptions()
    }

    private fun initProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(getString(R.string.please_wait))
        progressDialog.setCancelable(false)
    }

    open fun initUi() {
        getErrorView()?.setOnClickListener { onErrorViewClick() }
        getEmptyView()?.setOnClickListener { onEmptyViewClick() }
    }

    override fun initRxSubscriptions() {}

    override fun onErrorViewClick() {}

    override fun onEmptyViewClick() {}

    protected open fun getLoadingView(): View? {
        return loadingView
    }

    abstract fun getContentView(): View?

    protected open fun getErrorView(): View? {
        return errorView
    }

    protected open fun getEmptyView(): View? {
        return emptyView
    }

    override fun rxUnsubscribe() {
        compositeDisposable.clear()
    }

    override fun rxAddSubscription(disposable: Disposable?) {
        if (disposable != null) {
            compositeDisposable.add(disposable)
        }
    }

    override fun <T> restoreInstanceState(t: T, savedInstanceState: Bundle) {
        Icepick.restoreInstanceState(t, savedInstanceState)
    }

    override fun <T> saveInstanceState(t: T, savedInstanceState: Bundle) {
        Icepick.saveInstanceState(t, savedInstanceState)
    }

    override fun showContent() {
        getContentView().visible()
        getLoadingView().gone()
        getErrorView().gone()
        getEmptyView().gone()
    }

    override fun showLoading() {
        getContentView().gone()
        getLoadingView().visible()
        getErrorView().gone()
        getEmptyView().gone()
    }

    override fun showError(msg: String?) {
        getContentView().gone()
        getLoadingView().gone()
        getErrorView().visible()
        getEmptyView().gone()

        errorViewText?.text = msg ?: getString(R.string.oops_something_went_wrong)
    }

    override fun showError(msg: Int) {
        showError(getString(msg))
    }

    override fun showEmpty(msg: String?) {
        getContentView().gone()
        getLoadingView().gone()
        getErrorView().gone()
        getEmptyView().visible()

        emptyViewText?.text = msg ?: getString(R.string.no_data)
    }

    override fun showEmpty(msg: Int) {
        showEmpty(getString(msg))
    }

    override fun showMessage(msg: String?) {
        toastUtils.show(msg)
    }

    override fun showMessage(msg: Int) {
        toastUtils.show(msg)
    }

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        progressDialog.dismiss()
    }

    override fun onDestroy() {
        rxUnsubscribe()
        hideProgressDialog()
        super.onDestroy()
    }

}