package com.example.am951t.appinforeground;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by am951t on 6/8/2018.
 */

public class ResponseReceiver extends BroadcastReceiver {

    public static final String LOCAL_ACTION = "com.example.am951t.intent_service.ALL_DONE";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(ResponseReceiver.class.getSimpleName(),"OnReceive Boradcast: "+AppInForegroundService.getActivityInForeground(context));
        Toast.makeText(context, "App in foreground: "+intent.getStringExtra(AppInForegroundService.getActivityInForeground(context)), Toast.LENGTH_SHORT).show();
    }
}
