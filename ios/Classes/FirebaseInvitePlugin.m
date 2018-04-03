#import "FirebaseInvitePlugin.h"
#import <firebase_invite/firebase_invite-Swift.h>

@implementation FirebaseInvitePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFirebaseInvitePlugin registerWithRegistrar:registrar];
}
@end
