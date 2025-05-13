package com.example.common

import android.util.Log

object LogUtil {
    private val LOGGER_DEBUG = true
    private val TAG = "jtm"

    fun e(message: String?) {
        if (LOGGER_DEBUG) Log.e(TAG, buildLogMsg(message)!!)
    }

    fun w(message: String?) {
        if (LOGGER_DEBUG) Log.w(TAG, buildLogMsg(message)!!)
    }

    fun i(message: String?) {
        if (LOGGER_DEBUG) Log.i(TAG, buildLogMsg(message)!!)
    }

    fun d(message: String?) {
        if (LOGGER_DEBUG) Log.d(TAG, buildLogMsg(message)!!)
    }

    fun v(message: String?) {
        if (LOGGER_DEBUG) Log.v(TAG, buildLogMsg(message)!!)
    }

    fun buildLogMsg(message: String?): String? {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)
        return sb.toString()
    }
}