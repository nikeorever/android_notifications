import 'package:flutter/material.dart';
import 'package:android_notifications/android_notifications.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  AndroidNotificationManager _manager = const AndroidNotificationManager();
  static const int _notificationId = 1;

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('AndroidNotificationsPlugin example app'),
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
                            contentTitle: 'From Flutter',
                            contentText: 'Hello, Android, I am Flutter.');
                    await _manager.notify(_notificationId, notification);
                  },
                  child: Text('show android notification',
                      style: TextStyle(color: const Color(0xFFFFFFFF)))),
              Divider(height: 10),
              MaterialButton(
                  color: const Color(0xFF6483E9),
                  elevation: 10,
                  onPressed: () async {
                    await _manager.cancel(_notificationId);
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
