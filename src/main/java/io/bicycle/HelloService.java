package io.bicycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import io.bicycle.service.ServiceBinder;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:11 AM
 */
public class HelloService extends Service {

    public static final String HELLO_ACTION = "io.bicycle.HelloService.SEND_DATA";
    public static final String HELLO_MESSAGE = "message";

    private final IBinder binder = ServiceBinder.create(this);

    private int helloCount = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        helloCount += 1;

        Log.d("HelloService", "Incrementing");

        if (helloCount % 3 == 0) {
            sendHello();
        }

        return Service.START_NOT_STICKY;
    }

    private void sendHello() {
        Intent sayHi = new Intent(HELLO_ACTION);
        sayHi.putExtra(HELLO_MESSAGE, String.format("Hi ;-) (on count %d)", helloCount));
        LocalBroadcastManager.getInstance(this).sendBroadcast(sayHi);

        Log.d("HelloService", "Sent hello");
    }

    public String getHello() {
        return "Hello " + helloCount;
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }

}
