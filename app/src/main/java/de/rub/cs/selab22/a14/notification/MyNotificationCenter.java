package de.rub.cs.selab22.a14.notification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

import androidx.core.app.NotificationCompat;
import androidx.navigation.NavDeepLinkBuilder;

import java.util.Calendar;

import de.rub.cs.selab22.a14.R;
import de.rub.cs.selab22.a14.helper.ActivityRecorder;


/* Create an Object from the class MyNotificationCenter in the appropriate Class/interface and use
that object as the proxy for executing the functions below.
To summarize how to use Notifications: Just give the context (which will be "this" 99% of the time)
give a title and message as a String (add those to the strings file both EN and DE), give an intent
(this will be the class that opens/executes once the user clicks the notification), add a delay
(this is in millis (1000ms = 1s)) and lastly specific a notificationID in order to be able to cancel
on-going scheduledNotification. If you go for a repeating Notification you'll also need to specify
the frequency of the notification (for exact ones, it'll still be repeats on millis basis. For
inexact ones, it'll be the specified values, above the function "scheduledInexactRepeatingNotifications).
 */
public class MyNotificationCenter {

    public static MyNotificationCenter INSTANCE;

    public MyNotificationCenter() {}

    public static void init() {
        if (INSTANCE == null) {
            INSTANCE = new MyNotificationCenter();
        }
    }
    public void scheduleNotification(Context context, String title, String message, long delay) {
        PendingIntent moodPendingIntent =
                new NavDeepLinkBuilder(context.getApplicationContext())
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.nested_survey_graph)
                        .createPendingIntent();

        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);
        //.setSubText("Look, subtext!");

        mBuilder.setContentIntent(moodPendingIntent);
        Notification notification = mBuilder.build();
        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_CHANNEL, mChannel);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }


    /* ONLY VALID INPUTS FOR inexactInterval ARE:
    AlarmManager.INTERVAL_FIFTEEN_MINUTES = 15 * 60 * 1000
    AlarmManager.INTERVAL_HALF_HOUR = 2*INTERVAL_FIFTEEN_MINUTES
    AlarmManager.INTERVAL_HOUR = 2*INTERVAL_HALF_HOUR
    AlarmManager.INTERVAL_HALF_DAY = 12*INTERVAL_HOUR
    AlarmManager.INTERVAL_DAY = 2*INTERVAL_HALF_DAY
    */
    public void scheduleInexactRepeatingNotification(Context context, String title, String message,
                                                     long delay, long inexactInterval) {
        PendingIntent moodPendingIntent =
                new NavDeepLinkBuilder(context.getApplicationContext())
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.nested_survey_graph)
                        .createPendingIntent();

        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);
        //.setSubText("Look, subtext!");

        mBuilder.setContentIntent(moodPendingIntent);
        Notification notification = mBuilder.build();
        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 2);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_CHANNEL, mChannel);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 2, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, inexactInterval, pendingIntent);
    }

    public void scheduleExactRepeatingNotification(Context context, String title, String message, long intervalInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,22);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 00);

        PendingIntent moodPendingIntent =
                new NavDeepLinkBuilder(context.getApplicationContext())
                        .setGraph(R.navigation.nav_graph)
                        .setDestination(R.id.nested_survey_graph)
                        .createPendingIntent();

        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);
        //.setSubText("Look, subtext!");

        mBuilder.setContentIntent(moodPendingIntent);
        Notification notification = mBuilder.build();
        Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 3);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_CHANNEL, mChannel);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 3, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        long futureInMillis = calendar.getTimeInMillis();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, futureInMillis, intervalInMillis, pendingIntent);
    }

    public void cancelAlarm(Context context, int notificationId) {
        AlarmManager alarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context.getApplicationContext(), MyNotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), notificationId, intent, PendingIntent.FLAG_NO_CREATE);
        if (pendingIntent != null && alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}