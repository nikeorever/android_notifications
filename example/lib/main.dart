import 'package:flutter/material.dart';
import 'package:android_notifications/android_notifications.dart';
import 'package:android_notifications/android_notification_manager.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  AndroidNotificationManager _manager = const AndroidNotificationManager();

  @override
  void initState() {
    super.initState();
  }

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
