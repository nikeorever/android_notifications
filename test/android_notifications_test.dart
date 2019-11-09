import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_notifications/android_notifications.dart';

void main() {
  const MethodChannel channel = MethodChannel('android_notifications');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await AndroidNotifications.platformVersion, '42');
  });
}
