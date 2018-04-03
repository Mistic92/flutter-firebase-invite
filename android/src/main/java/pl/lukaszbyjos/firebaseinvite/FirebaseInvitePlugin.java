package pl.lukaszbyjos.firebaseinvite;


import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.appinvite.AppInviteInvitation.IntentBuilder;
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

        String custom_image = args.get("custom_image");
        String deep_link = args.get("deep_link");
        String cta = args.get("cta");
        String email_html_content = args.get("email_html_content");
        String email_subject = args.get("email_subject");
        String analytics_id = args.get("analytics_id");
        IntentBuilder intentBuilder = new IntentBuilder(args.get("title"))
            .setMessage(args.get("message"))
            .setEmailHtmlContent(email_html_content)
            .setEmailSubject(email_subject)
            .setGoogleAnalyticsTrackingId(analytics_id);
        if(cta!=null && cta.length()>2){
            intentBuilder.setCallToActionText(cta);
        }
        if (deep_link != null) {
            intentBuilder.setDeepLink(Uri.parse(deep_link));
        }
        if (custom_image != null) {
            intentBuilder.setCustomImage(Uri.parse(custom_image));
        }
        Intent intent = intentBuilder.build();

        registrar.activity().startActivityForResult(intent, REQUEST_INVITE);
        result.success(null);
    }

    @Override
    public boolean onActivityResult(int i, int i1, Intent intent) {
        return false;
    }
}
