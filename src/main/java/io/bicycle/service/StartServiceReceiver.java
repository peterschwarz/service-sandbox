package io.bicycle.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:22 AM
 */
public class StartServiceReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, HelloService.class));
    }
}
