package io.bicycle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.bicycle.service.Scheduler;

import java.util.Calendar;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 11:21 AM
 */
public class ScheduleReceiver extends BroadcastReceiver {
    // Restart service every 30 seconds
    private static final long REPEAT_TIME = 1000 * 30;

    @Override
    public void onReceive(Context context, Intent intent) {
        new Scheduler(HelloServiceStarter.class)
                .withTriggerDelay(Calendar.SECOND, 30)
                .withInterval(REPEAT_TIME)
                .schedule(context);

    }
}