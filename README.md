# android_notifications

Flutter plugin for display notifications, Not supported on iOS
```dart
import 'package:android_notifications/android_notifications.dart';

class _MyAppState extends State<MyApp> {
  AndroidNotificationManager _manager = const AndroidNotificationManager();
  
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              MaterialButton(
                  color: const Color(0xFF6483E9),
                  elevation: 10,
                  onPressed: () async {
                    AndroidNotification notification =
                        const AndroidNotification(
                            contentTitle: 'from flutter',
                            contentText: 'hello, android');
                    await _manager.notify(1, notification);
                  },
                  child: Text('show android notification',
                      style: TextStyle(color: const Color(0xFFFFFFFF)))),
              Divider(height: 10),
              MaterialButton(
                  color: const Color(0xFF6483E9),
                  elevation: 10,
                  onPressed: () async {
                    await _manager.cancel(1);
                  },
                  child: Text('cancel android notification',
                      style: TextStyle(color: const Color(0xFFFFFFFF)))),
            ],
          ),
        ),
      ),
    );
  }
}
```
## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.dev/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.
