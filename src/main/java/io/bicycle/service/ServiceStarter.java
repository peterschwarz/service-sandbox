package io.bicycle.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceStarter extends BroadcastReceiver {
    private final Class<? extends Service> serviceClass;

    public ServiceStarter(Class<? extends Service> serviceClass) {
        this.serviceClass = serviceClass;
    }

    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, serviceClass));
    }
}