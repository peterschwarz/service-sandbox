package io.bicycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:11 AM
 */
public class HelloService extends Service {

    private final IBinder binder = ServiceBinder.create(this);

    private int helloCount = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        helloCount += 1;

        Log.d("HelloService", "Incrementing");

        return Service.START_NOT_STICKY;
    }

    public String getHello() {
        return "Hello " + helloCount;
    }


    public IBinder onBind(Intent intent) {
        return binder;
    }


}
