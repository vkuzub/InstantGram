package com.vkuzub.instantgram.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Collection<*>?.isNullOrEmpty() = this == null || isEmpty()

fun Array<*>?.isNullOrEmpty() = this == null || isEmpty()

fun Map<*, *>?.isNullOrEmpty() = this == null || isEmpty()

fun Date?.dateToString(pattern: String = "MMM dd, yyyy, hh:mm aaa"): String {
    if (this == null) "Unknown"
    val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    return dateFormat.format(this)
}