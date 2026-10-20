package com.gathin.tvhome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

/** After the TV boots, bring the clean home to the foreground so a watchdog-restored stock home
 *  (PatchWall) cannot grab the screen. The system also auto-starts this app as the only HOME;
 *  this receiver is the safety net for the case where the stock home comes back. */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) return;
        final Handler h = new Handler(Looper.getMainLooper());
        h.postDelayed(() -> {
            try {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } catch (Exception ignored) {
            }
        }, 1500);
    }
}
