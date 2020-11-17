import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:platform/platform.dart';
import 'package:android_notifications/android_notifications.dart';

void main() {
  AndroidNotification notification;

  MockMethodChannel mockChannel;
  setUp(() {
    mockChannel = MockMethodChannel();
  });

  group('AndroidNotifications Test', () {
    test('Test on Android platform', () async {
      notification = AndroidNotification(
        contentTitle: "From Flutter",
        contentText: 'Hello, Android, I am Flutter.',
      );

      var manager = AndroidNotificationManager.private(
          channel: mockChannel,
          platform: FakePlatform(operatingSystem: 'android'));
      manager.notify(1, notification, tag: null);
      verify(mockChannel.invokeMethod<void>('notify', <String, Object>{
        'tag': null,
        'id': 1,
        'contentTitle': "From Flutter",
        'contentText': 'Hello, Android, I am Flutter.'
      }));
    });

    test('Test on iOS platform', () async {
      notification = AndroidNotification(
        contentTitle: "From Flutter",
        contentText: 'Hello, iOS, I am Flutter.',
      );

      var manager = AndroidNotificationManager.private(
          channel: mockChannel, platform: FakePlatform(operatingSystem: 'ios'));
      manager.notify(1, notification);
      verifyZeroInteractions(mockChannel);
    });
  });
}

class MockMethodChannel extends Mock implements MethodChannel {}
