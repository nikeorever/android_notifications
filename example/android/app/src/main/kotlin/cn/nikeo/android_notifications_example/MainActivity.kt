package cn.nikeo.android_notifications_example

import cn.nikeo.flutter.plugins.androidnotifications.AndroidNotificationsPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        cn.nikeo.android_notifications_example.R.mipmap.ic_launcher
//        flutterEngine.plugins.add(AndroidNotificationsPlugin(R.mipmap.ic_launcher))
    }
}
