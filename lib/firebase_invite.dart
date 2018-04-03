import 'dart:async';

import 'package:flutter/services.dart';
import 'package:meta/meta.dart';

class FirebaseInvite {
  static const MethodChannel _channel = const MethodChannel('firebase_invite');

  static FirebaseInvite get instance => new FirebaseInvite();

  static Future<Null> onInviteClick(
      {@required String title,
      @required String message,
      String ctaText,
      String deepLinkUri,
      String customImageUri,
      String emailHtmlContent,
      String emailSubject,
      String analyticsId}) async {
    Map<String, String> args = {};
    args.putIfAbsent("title", () => title);
    args.putIfAbsent("message", () => message);
    args.putIfAbsent("ctaText", () => ctaText);
    args.putIfAbsent("deep_link", () => deepLinkUri);
    args.putIfAbsent("custom_image", () => customImageUri);
    args.putIfAbsent("email_html_content", () => emailHtmlContent);
    args.putIfAbsent("email_subject", () => emailSubject);
    args.putIfAbsent("analytics_id", () => analyticsId);
    Map<String, Map<String, String>> arguments = {'arguments': args};
    await _channel.invokeMethod("onInviteClicked", arguments);
  }
}
