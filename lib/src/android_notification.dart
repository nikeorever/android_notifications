import 'package:meta/meta.dart';

class AndroidNotification {
  /// Create an basic Android Notification with the following parameters
  ///
  /// [contentTitle] Set the title (first row) of the notification, in a standard notification.
  /// [contentText] Set the text (second row) of the notification, in a standard notification.
  const AndroidNotification(
      {@required this.contentTitle, @required this.contentText})
      : assert(contentTitle != null),
        assert(contentText != null);

  final String contentTitle;
  final String contentText;
}
