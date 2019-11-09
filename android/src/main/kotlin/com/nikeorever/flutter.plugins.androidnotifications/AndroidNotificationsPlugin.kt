package com.nikeorever.flutter.plugins.androidnotifications

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.PluginRegistry.Registrar

class AndroidNotificationsPlugin: FlutterPlugin {

  private var helper: NotificationManagerHelper = NotificationManagerHelper()
  private var impl: MethodCallHandlerImpl= MethodCallHandlerImpl(helper)

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val helper = NotificationManagerHelper()
      val impl = MethodCallHandlerImpl(helper)
      impl.startListening(registrar.messenger())
    }
  }

  override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    helper.createNotificationManager(binding.applicationContext)
    impl.startListening(binding.flutterEngine.dartExecutor)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    helper.destroyNotificationManager()
    impl.stopListening()
  }
}
