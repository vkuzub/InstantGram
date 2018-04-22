package com.vkuzub.instantgram.base

import android.os.Bundle

interface IcepickSupport {

    fun <T> restoreInstanceState(t: T, savedInstanceState: Bundle)

    fun <T> saveInstanceState(t: T, savedInstanceState: Bundle)
}