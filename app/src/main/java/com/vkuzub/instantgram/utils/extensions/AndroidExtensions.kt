package com.vkuzub.instantgram.utils.extensions

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Bitmap
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vkuzub.instantgram.R
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Viacheslav on 26.02.2018.
 */
fun Context.showKeyboard(view: View?) {
    view?.let {
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.showSoftInput(it, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Context.hideKeyboard(view: View?) {
    view?.let {
        val imm: InputMethodManager? = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun View?.gone() {
    if (this != null) visibility = View.GONE
}

fun View?.visible() {
    if (this != null) visibility = View.VISIBLE
}

fun View?.invisible() {
    if (this != null) visibility = View.INVISIBLE
}

fun Button?.setActive(drawableBackground: Int) {
    if (this != null) {
        setBackgroundResource(drawableBackground)
        isEnabled = true
    }
}

fun Button?.setNonActive(drawableBackground: Int) {
    if (this != null) {
        setBackgroundResource(drawableBackground)
        isEnabled = false
    }
}

fun View?.backgroundColor(layoutRes: Int) {
    if (this != null) setBackgroundColor(context.resources.getColor(layoutRes))
}

fun ImageView?.setImageDrawable(resId: Int) {
    if (this != null) setImageDrawable(context.resources.getDrawable(resId))
}

fun View?.setBackgroundDrawable(resId: Int) {
    if (this != null) setBackgroundDrawable(context.resources.getDrawable(resId))
}

fun ViewGroup.inflate(layoutRes: Int, attach: Boolean): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attach)
}

fun View?.setBackgroundTint(colorResId: Int) {
    if (this != null) ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(context.resources.getColor(colorResId)))
}

fun Context.isPortraitOrientation(): Boolean {
    val orientation = resources.configuration.orientation
    return orientation == Configuration.ORIENTATION_PORTRAIT
}

fun ImageView?.glide(progressBar: ProgressBar?, url: String?, centerCrop: Boolean = true,
                     transformation: Transformation<Bitmap>? = null, errorDrawable: Int = R.drawable.cloud_off) {
    if (this == null) return
    if (url.isNullOrEmpty()) {
        val drawableRequestBuilder = Glide.with(context).load(errorDrawable)
        if (transformation != null) {
            drawableRequestBuilder.bitmapTransform(transformation)
        }
        drawableRequestBuilder.crossFade().into(this)
        progressBar.gone()
        return
    }

    progressBar.visible()
    val drawableRequestBuilder = Glide.with(context).load(url)
    if (centerCrop) {
        drawableRequestBuilder.centerCrop()
    }

    if (transformation != null) {
        drawableRequestBuilder.bitmapTransform(transformation)
    }

    drawableRequestBuilder.diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().error(errorDrawable)
            .listener(object : RequestListener<String, GlideDrawable> {
                override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                    progressBar.gone()
                    return false
                }

                override fun onResourceReady(resource: GlideDrawable?,
                                             model: String?, target: Target<GlideDrawable>?,
                                             isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                    progressBar.gone()
                    return false
                }
            }).into(this)
}

const val UNDEFINED = "Undefined"
const val TIME_INPUT = "yyyy-MM-dd'T'HH:mm:ssZ" //2018-02-07T13:04:58+02:00
const val TIME_OUTPUT = "HH:mm:ss, MMM yy"

fun String?.formatTime(inputType: String = TIME_INPUT, outputType: String = TIME_OUTPUT): String {
    if (this.isNullOrEmpty()) return UNDEFINED
    try {
        val formatter = DateTimeFormat.forPattern(TIME_INPUT)
        formatter.withLocale(Locale.getDefault())
        return formatter.parseDateTime(this).toString(outputType)
    } catch (e: Exception) {
        Timber.e(e)
    }
    return UNDEFINED
}

fun Date.formatTime(outputType: String = TIME_INPUT): String {
    val simpleDateFormat = SimpleDateFormat(outputType, Locale.getDefault())
    return simpleDateFormat.format(this)
}