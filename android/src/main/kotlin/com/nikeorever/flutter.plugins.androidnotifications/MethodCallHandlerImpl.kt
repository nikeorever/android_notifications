package com.nikeorever.flutter.plugins.androidnotifications

import androidx.annotation.RestrictTo
import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

private const val TAG = "MethodCallHandlerImpl"

@RestrictTo(value = [RestrictTo.Scope.LIBRARY])
internal class MethodCallHandlerImpl(private val helper: NotificationManagerHelper) : MethodChannel.MethodCallHandler {

    private var channel: MethodChannel? = null

    fun startListening(messenger: BinaryMessenger) {
        if (channel != null) {
            Log.wtf(TAG, "Setting a method call handler before the last was disposed.")
            stopListening()
        }

        channel = MethodChannel(messenger, "plugins.flutter.nikeorever.com/android_notifications")
        channel!!.setMethodCallHandler(this)
    }

    fun stopListening() {
        if (channel == null) {
            Log.wtf(TAG, "Tried to stop listening when no methodChannel had been initialized.")
            return
        }

        channel!!.setMethodCallHandler(null)
        channel = null
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        call.arguments<Map<String, Any>>().also {
            val tag = it["tag"] as String?
            val id = it["id"] as Int
            when (call.method) {
                "notify" -> {
                    val contentTitle = it["contentTitle"] as String
                    val contentText = it["contentText"] as String
                    helper.notify(tag, id) { context ->
                        primaryNotification(context, contentTitle, contentText).build()
                    }
                    result.success(null)
                }
                "cancel" -> {
                    helper.cancel(tag, id)
                    result.success(null)
                }
                else -> {
                }
            }
        }
    }
}