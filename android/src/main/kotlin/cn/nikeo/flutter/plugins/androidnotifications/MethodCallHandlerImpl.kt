package cn.nikeo.flutter.plugins.androidnotifications

import androidx.annotation.RestrictTo
import io.flutter.Log
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

private const val TAG = "MethodCallHandlerImpl"

@RestrictTo(value = [RestrictTo.Scope.LIBRARY])
internal class MethodCallHandlerImpl(
        private val helper: NotificationManagerHelper,
        private val plugin: AndroidNotificationsPlugin
) : MethodChannel.MethodCallHandler {

    private var channel: MethodChannel? = null

    fun startListening(messenger: BinaryMessenger) {
        if (channel != null) {
            Log.wtf(TAG, "Setting a method call handler before the last was disposed.")
            stopListening()
        }

        channel = MethodChannel(messenger, "plugins.flutter.nikeo.cn/android_notifications")
                .also { channel ->
                    channel.setMethodCallHandler(this)
                }
    }

    fun stopListening() {
        if (channel == null) {
            Log.wtf(TAG, "Tried to stop listening when no methodChannel had been initialized.")
            return
        }

        checkNotNull(channel).setMethodCallHandler(null)
        channel = null
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        call.arguments<Map<String, Any>>().also { args ->
            val tag = args["tag"] as String?
            val id = requireNotNull(args["id"] as Int?) {
                "Tried to show a notification on Android but missing id."
            }
            when (call.method) {
                "notify" -> {
                    val contentTitle = requireNotNull(args["contentTitle"] as? String) {
                        "Tried to show a notification on Android but missing contentTitle."
                    }
                    val contentText = requireNotNull(args["contentText"] as? String) {
                        "Tried to show a notification on Android but missing contentText."
                    }
                    helper.notify(tag, id) { context ->
                        primaryNotificationBuilder(
                                context = context,
                                contentTitle = contentTitle,
                                contentText = contentText,
                                smallIcon = if (plugin.smallIcon == ID_NULL) {
                                    helper.defaultSmallIcon()
                                } else {
                                    plugin.smallIcon
                                }
                        ).build()
                    }
                    result.success(null)
                }
                "cancel" -> {
                    helper.cancel(tag, id)
                    result.success(null)
                }
            }
        }
    }
}