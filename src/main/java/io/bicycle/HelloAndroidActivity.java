package io.bicycle;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import io.bicycle.service.Scheduler;
import io.bicycle.service.ServiceSupport;

public class HelloAndroidActivity extends Activity {

    private ServiceSupport<HelloService> serviceSupport;

    private TextView helloText;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceSupport = ServiceSupport.create(this, HelloService.class);

        helloText = (TextView) findViewById(R.id.helloText);

        new Scheduler(HelloServiceStarter.class)
                .withInterval(30 * 1000)
                .schedule(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        serviceSupport.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(HelloService.HELLO_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();

        serviceSupport.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(io.bicycle.R.menu.main, menu);
        return true;
    }

    public void onClick(@SuppressWarnings("UnusedParameters") View view) {
        if(serviceSupport.serviceAvailable()) {
            helloText.setText(serviceSupport.getService().getHello());
        }
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Extract data included in the Intent
            String message = intent.getStringExtra(HelloService.HELLO_MESSAGE);

            Log.d("Hello.receiver", "Got message: " + message);

            helloText.setText(String.format("Message Received: %s", message));
        }
    };

}

