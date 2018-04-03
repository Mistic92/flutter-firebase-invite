package pl.lukaszbyjos.firebaseinvite;


import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.appinvite.AppInviteInvitation;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import java.util.Map;

/**
 * FirebaseInvitePlugin
 */
public class FirebaseInvitePlugin implements MethodCallHandler,
    PluginRegistry.ActivityResultListener {

    private static final int REQUEST_INVITE = 1321;
    private final PluginRegistry.Registrar registrar;

    FirebaseInvitePlugin(PluginRegistry.Registrar registrar) {
        this.registrar = registrar;
    }

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "firebase_invite");
        channel.setMethodCallHandler(new FirebaseInvitePlugin(registrar));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {

        if (call.method.equals("onInviteClicked")) {
            onInviteClicked(call, result);
        } else {
            result.notImplemented();
        }

//        switch (call.method) {
//            case "onInviteClicked":
//                onInviteClicked(call, result);
//                break;
//            default:
//                result.notImplemented();
//                break;
//        }
    }

    private void onInviteClicked(MethodCall call, Result result) {
        Map<String, String> args = call.argument("arguments");

        Intent intent = new AppInviteInvitation.IntentBuilder(args.get("title"))
            .setMessage(args.get("message"))
            .setDeepLink(Uri.parse(args.get("deep_link")))
            .setCustomImage(Uri.parse(args.get("custom_image")))
            .setCallToActionText(args.get("cta"))
            .setEmailHtmlContent(args.get("email_html_content"))
            .setEmailSubject(args.get("email_subject"))
            .setGoogleAnalyticsTrackingId(args.get("analytics_id"))
            .build();
        registrar.activity().startActivityForResult(intent, REQUEST_INVITE);
        result.success(null);
    }

    @Override
    public boolean onActivityResult(int i, int i1, Intent intent) {
        return false;
    }
}
