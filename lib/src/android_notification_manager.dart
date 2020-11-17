import 'package:flutter/services.dart';
import 'package:meta/meta.dart';
import 'package:platform/platform.dart';
import 'android_notification.dart';

const String _kChannelName = 'plugins.flutter.nikeo.cn/android_notifications';

/// A class for managing [AndroidNotification].
class AndroidNotificationManager {
  const AndroidNotificationManager({Platform platform})
      : _channel = const MethodChannel(_kChannelName),
        _platform = platform ?? const LocalPlatform();

  @visibleForTesting
  const AndroidNotificationManager.private(
      {@required MethodChannel channel, @required Platform platform})
      : _channel = channel,
        _platform = platform;

  final MethodChannel _channel;
  final Platform _platform;

  /// This works only on Android platforms
  ///
  /// Posts a notification to be shown in the status bar.
  /// If a notification with the same tag and id has already been posted
  /// by your application and has not yet been canceled, it will be replaced
  /// by the updated information.
  ///
  /// [id] An identifier for this notification. The pair (tag, id) must be unique within your application.
  /// [notification] Notification
  /// [tag] A string identifier for this notification. May be null.

  Future<void> notify(int id, AndroidNotification notification,
      {String tag}) async {
    if (!_platform.isAndroid) {
      return;
    }

    final Map<String, dynamic> args = <String, dynamic>{'tag': tag, 'id': id};
    args['contentTitle'] = notification.contentTitle;
    args['contentText'] = notification.contentText;
    await _channel.invokeMethod<void>('notify', args);
  }

  /// This works only on Android platforms
  ///
  /// Cancel a previously shown notification. If it's transient,
  /// the view will be hidden. If it's persistent,
  /// it will be removed from the status bar.
  ///
  /// [id] An identifier for this notification. The pair (tag, id) must be unique within your application.
  /// [tag] A string identifier for this notification. May be null.
  Future<void> cancel(int id, {String tag}) async {
    if (!_platform.isAndroid) {
      return;
    }
    await _channel
        .invokeMethod<void>('cancel', <String, dynamic>{'tag': tag, 'id': id});
  }
}
