package com.ccspart2.pokeswap_android.utils

import timber.log.Timber

object LogUtils {
    private const val TAG = "PokeSwap"

    fun debug(
        msg: String,
        tag: String = TAG,
    ) = Timber.tag(tag).d(msg)

    fun warn(
        msg: String,
        tag: String = TAG,
        throwable: Throwable? = null,
    ) = Timber.tag(tag).w(throwable, msg)

    fun error(
        msg: String,
        tag: String = TAG,
        throwable: Throwable? = null,
    ) = Timber.tag(tag).e(throwable, msg)

    fun info(
        msg: String,
        tag: String = TAG,
    ) = Timber.tag(tag).i(msg)

    fun verbose(
        msg: String,
        tag: String = TAG,
    ) = Timber.tag(tag).v(msg)
}
