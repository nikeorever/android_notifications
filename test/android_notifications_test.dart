import 'package:android_notifications/android_notification_manager.dart';
import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:android_notifications/android_notifications.dart';
import 'package:meta/meta.dart';
import 'package:mockito/mockito.dart';
import 'package:platform/platform.dart';

void main() {
  AndroidNotification notification;

  MockMethodChannel mockChannel;
  setUp(() {
    mockChannel = MockMethodChannel();
  });

  group('AndroidNotifications', () {
    test('pass right params', () async {
      notification = AndroidNotification(
        contentTitle: "from fluter",
        contentText: 'hello, android',
      );

      var manager = AndroidNotificationManager.private(
          channel: mockChannel,
          platform: FakePlatform(operatingSystem: 'android'));
      manager.notify(1, notification, tag: null);
      verify(mockChannel.invokeMethod<void>('notify', <String, Object>{
        'tag': null,
        'id': 1,
        'contentTitle': "from fluter",
        'contentText': 'hello, android'
      }));
    });
    test('pass null value to title', () {
      expect(() {
        AndroidNotification(contentTitle: null, contentText: 'hello, android');
      }, throwsAssertionError);
    });

    test('call in ios platform', () async {
      notification = AndroidNotification(
        contentTitle: "from fluter",
        contentText: 'hello, ios',
      );

      var manager = AndroidNotificationManager.private(
          channel: mockChannel, platform: FakePlatform(operatingSystem: 'ios'));
      manager.notify(1, notification);
      verifyZeroInteractions(mockChannel);
    });
  });
}

class MockMethodChannel extends Mock implements MethodChannel {}
