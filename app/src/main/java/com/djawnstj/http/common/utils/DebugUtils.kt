package com.djawnstj.http.common.utils

import android.util.Log

var isDebug: Boolean = true

fun debug(tag: String, msg: String?) {
    if (isDebug) Log.d(tag, msg ?: "message is null")
}

fun error(tag: String, msg: String?) {
    if (isDebug) Log.e(tag, msg ?: "message is null")
}

fun error(tag: String, msg: String?, t: Throwable) {
    if (isDebug) Log.e(tag, msg, t)
}