package com.snowman.baselibrary.utils

import android.util.Log

class LogUtil {

    fun log(message: String) {
        Log.d(TAG, message)
    }

    companion object {
        var TAG = "SnowMan"
    }
}
