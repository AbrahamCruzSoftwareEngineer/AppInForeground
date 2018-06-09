package com.example.am951t.appinforeground;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by am951t on 6/8/2018.
 */

public class AppInForegroundService extends IntentService {

    public static final String TEXT_OUTPUT = "outText";
    public static String mActivityInForeground;
    public Context context;

    public AppInForegroundService(String name) {
        super(name);
    }
    public AppInForegroundService() {
        super("AppInForegroundService");
        this.context = this;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        setWhoIsInForeground(context);

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ResponseReceiver.LOCAL_ACTION);
        broadcastIntent.putExtra(TEXT_OUTPUT, getActivityInForeground(context));
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.sendBroadcast(broadcastIntent);
    }

    public static void setWhoIsInForeground(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService( Context.ACTIVITY_SERVICE );
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses){
            if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                mActivityInForeground = appProcess.processName;
                Log.i("Foreground App", appProcess.processName);
            }
        }
    }

    public static String getActivityInForeground(Context ctx) {
        ActivityManager activityManager = (ActivityManager) ctx.getSystemService( Context.ACTIVITY_SERVICE );
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo appProcess : appProcesses){
            if(appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND){
                mActivityInForeground = appProcess.processName;
                Log.i("Foreground App", appProcess.processName);
            }
        }
        Log.i(AppInForegroundService.class.getSimpleName(),"AppInForegroundService getActivityInForeground: "+mActivityInForeground);
        Toast.makeText(ctx, "App in foreground: "+mActivityInForeground, Toast.LENGTH_SHORT).show();
        return mActivityInForeground;
    }
}
