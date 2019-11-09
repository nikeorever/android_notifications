#import "AndroidNotificationsPlugin.h"
#import <android_notifications/android_notifications-Swift.h>

@implementation AndroidNotificationsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftAndroidNotificationsPlugin registerWithRegistrar:registrar];
}
@end
