package com.driveutilities.hussainshabbir.driveutilities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.location.*;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.GridView;
import android.widget.AdapterView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("status",false);
        editor.commit();
        GridView gridView = (GridView)findViewById(R.id.GridView);
        final String[] details = new String[]
                {"Car Parking", "Parking Meter", "Gas Stations", "Hospitals","Restaurants","Hotels"};
        gridView.setAdapter(new UtilitiesAdpater(this,details));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(),AlarmActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), (position > 1) ? MapLocation.class : SecondActivity.class);
                    intent.putExtra("key", details[position]);
                    startActivity(intent);
                }
            }
        });
    }
}
