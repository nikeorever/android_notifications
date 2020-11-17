package cn.nikeo.flutter.plugins.androidnotifications

import android.util.Log
import io.flutter.embedding.engine.plugins.FlutterPlugin

/**
 * A flutter plugin for managing [android.app.Notification] on Android.
 *
 * [smallIcon] The small icon to use in the notification layouts. it`s a resource ID in the
 * application's package of the drawable to use.
 */
class AndroidNotificationsPlugin(val smallIcon: Int) : FlutterPlugin {

    constructor() : this(-1)

    private lateinit var impl: MethodCallHandlerImpl

    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        Log.i("AndroidNotifications", binding.applicationContext.packageName)
        impl = MethodCallHandlerImpl(
                helper = NotificationManagerHelper(binding.applicationContext),
                plugin = this
        )
        impl.startListening(binding.binaryMessenger)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        impl.stopListening()
    }
}
