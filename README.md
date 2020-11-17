# android_notifications

[![pub package](https://img.shields.io/badge/pub-1.0.0-blueviolet.svg)](https://pub.dev/packages/android_notifications)

A Flutter plugin for managing notification on Android Platform. 

### Usage:
1. Create a notification.
```dart
AndroidNotification notification = const AndroidNotification(
    contentTitle: 'From Flutter',
    contentText: 'Hello, Android, I am Flutter.'
);
```
2. Posts a notification to be shown in the status bar (**async**).
```dart
AndroidNotificationManager _manager = const AndroidNotificationManager();
static const int _notificationId = 1;

void notify(AndroidNotification notification) async {
     await _manager.notify(_notificationId, notification);
}
 ```
3. Cancel a previously shown notification (**async**).
```dart
AndroidNotificationManager _manager = const AndroidNotificationManager();
static const int _notificationId = 1;

void cancel() async {
     await _manager.cancel(_notificationId);
}
```

### About small icon
To create Notification in Android, you must set the small icon by `setSmallIcon()`, Otherwise it will throw an exception(**error, Invalid notification (no valid small icon)**), The small icon resource used by this plugin by default is *{your_package_name}.R.mipmap.ic_launcher*, if you do not want to use it as a notification icon, please register the plugin manually and set the small icon in the following way.
```kotlin
class MainActivity : FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        val plugin = AndroidNotificationsPlugin(smallIcon = R.mipmap.ic_launcher)
        flutterEngine.plugins.add(plugin)
    }
}
```

### Links
[Build Android Notification](https://developer.android.com/training/notify-user/build-notification)