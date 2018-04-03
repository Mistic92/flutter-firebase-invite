import 'dart:async';

import 'package:flutter/services.dart';
import 'package:meta/meta.dart';

class FirebaseInvite {
  static const MethodChannel _channel = const MethodChannel('firebase_invite');

  static FirebaseInvite get instance => new FirebaseInvite();

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  Future<Null> onInviteClick(
      {@required String title,
      @required String message,
      Map<String, String> additional}) async {
    Map<String,String> args = {};
    if(additional!=null){
    args.addAll(additional);}
    args.putIfAbsent("title", () => title);
    args.putIfAbsent("message", () => message);
    await _channel.invokeMethod("onInviteClicked",args);
  }
}
