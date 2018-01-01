package com.driveutilities.hussainshabbir.driveutilities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class AlarmActivity extends BaseActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Parking Meter");
        timePicker = (TimePicker)findViewById(R.id.alarmPicker);
        ToggleButton toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        toggleButton.setChecked(sharedPreferences.getBoolean("status",false));
    }

    public void onToggleClicked(View view) {
        Intent intent = new Intent(AlarmActivity.this,AlarmReceiver.class);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (alarmManager == null) {
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
            calendar.set(Calendar.MINUTE,timePicker.getMinute());
            editor.putBoolean("status",true);
            editor.commit();
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this,0,intent,0);
            alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        } else {
            editor.putBoolean("status",false);
            editor.commit();
            alarmManager.cancel(pendingIntent);
            sendBroadcast(intent);
            alarmManager = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.finish();
    }
}