package com.evgeny.app.jojoengrish.crash_handler;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.evgeny.app.jojoengrish.activities.LoadingActivity;
import com.evgeny.app.jojoengrish.api.DbHelper;

public class DatabaseHandler implements Thread.UncaughtExceptionHandler {
    private Activity activity;
    public DatabaseHandler(Activity a) {
        activity = a;
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        SharedPreferences.Editor editor = settings.edit();
        new AlertDialog.Builder(activity)
                .setTitle("Database fix")
                .setMessage("Pls give me time to rebuild the database after an error")
                .setPositiveButton("Ok", null)
                .show();
        editor.putString("dbVer", "0");
        editor.apply();
        new AlertDialog.Builder(activity)
                .setTitle("Database fix. If it doesn't help contact the dev.")
                .setMessage("Done")
                .setPositiveButton("Ok", null)
                .show();
        Intent intent = new Intent(activity, LoadingActivity.class);
        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) MyApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
        activity.finish();
        System.exit(2);
    }
}
