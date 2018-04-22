package com.vkuzub.instantgram.utils.impl

import android.content.Context
import android.widget.Toast
import com.vkuzub.instantgram.utils.ToastUtils
import javax.inject.Inject

class ToastUtilsImpl @Inject constructor(val context: Context) : ToastUtils {

    companion object {
        private var toast: Toast? = null
        private val duration = Toast.LENGTH_SHORT
    }

    private fun makeText(context: Context, text: String): Toast {
        cancel()
        toast = Toast.makeText(context, text, duration)
        return toast!!
    }

    private fun makeText(context: Context, resId: Int): Toast {
        cancel()
        toast = Toast.makeText(context, resId, duration)
        return toast!!
    }

    override fun show(text: String?) {
        makeText(context, text ?: "").show()
    }

    override fun show(resId: Int) {
        makeText(context, resId).show()
    }

    private fun cancel() {
        toast?.cancel()
    }
}