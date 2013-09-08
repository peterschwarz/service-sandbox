package io.bicycle.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * User: pschwarz
 * Date: 9/8/13
 * Time: 12:08 PM
 */
public class Scheduler {

    private long interval = 1000 * 30;

    private int delayType = Calendar.SECOND;
    private int delay = 0;

    private Class<? extends BroadcastReceiver> broadcastReceiverClass;

    public Scheduler(Class<? extends BroadcastReceiver> broadcastReceiverClass) {
        this.broadcastReceiverClass = broadcastReceiverClass;
    }

    public Scheduler withInterval(long interval) {
        this.interval = interval;
        return this;
    }

    public Scheduler withTriggerDelay(int delayType, int delay) {
        this.delayType = delayType;
        this.delay = delay;
        return this;
    }


    public void schedule(Context context) {
        getAlarmManager(context).setInexactRepeating(AlarmManager.RTC_WAKEUP,
                triggerAt(), interval, pendingIntent(context));
    }

    public void scheduleExact(Context context) {
        getAlarmManager(context).setRepeating(AlarmManager.RTC_WAKEUP, triggerAt(),
                interval, pendingIntent(context));
    }

    private AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);
    }

    private PendingIntent pendingIntent(Context context) {
        Intent i = new Intent(context, broadcastReceiverClass);
        return PendingIntent.getBroadcast(context, 0, i,
                PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private long triggerAt() {
        Calendar cal = Calendar.getInstance();
        cal.add(delayType, delay);

        return cal.getTimeInMillis();
    }
}
