package io.bicycle.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:34 AM
 */
public class ServiceSupport<T> {

    private final Context context;
    private final Class<T> serviceType;

    private T service;

    public static <T> ServiceSupport<T> create(Context context, Class<T> serviceType) {
        return new ServiceSupport<T>(context, serviceType);
    }

    private ServiceSupport(Context context, Class<T> serviceType) {
        this.context = context;
        this.serviceType = serviceType;
    }

    public boolean serviceAvailable() {
        return service != null;
    }

    public T getService() {
        if(!serviceAvailable())
            throw new IllegalStateException("Check for service availability before calling this method");

        return service;
    }

    public void onResume() {
        context.bindService(new Intent(context, serviceType), mConnection, Context.BIND_AUTO_CREATE);
    }

    public void onPause() {
        context.unbindService(mConnection);
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder binder) {
            //noinspection unchecked
            service = ((ServiceBinder<T>) binder).getService();
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT)
                    .show();
        }

        public void onServiceDisconnected(ComponentName className) {
            service = null;
        }
    };
}
