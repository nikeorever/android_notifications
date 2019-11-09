import 'dart:async';

import 'package:flutter/services.dart';

class AndroidNotifications {
  static const MethodChannel _channel =
      const MethodChannel('android_notifications');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
