package com.driveutilities.hussainshabbir.driveutilities;


import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;


/**`
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {
    TextView hourTxtvw;
    TextView minTxtvw;
    TextView secTxtvw;
    int counter = 0;
    Handler handler = new Handler();

    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            counter += 1;
            int hour = (counter / 3600);
            int minute = (counter / 60);
            minute = (minute >=60) ? (minute - 60): minute;
            int second = (counter % 60);
            hourTxtvw.setText(String.format("%s:",getTimeString(hour)));
            minTxtvw.setText(String.format("%s:",getTimeString(minute)));
            secTxtvw.setText(getTimeString(second));
            handler.postDelayed(timerRunnable,1000);
        }
    };

    private String getTimeString(int time) {
        String value = String.valueOf(time);
        if (value.length() == 1) {
            value = String.format("0%s",value);
        }
        return value;
    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_container, container, false);
        final Button button = (Button)view.findViewById(R.id.button);
        final Button reset = (Button)view.findViewById(R.id.reset_button);
        hourTxtvw = (TextView)view.findViewById(R.id.Hour);
        minTxtvw = (TextView)view.findViewById(R.id.Minute);
        secTxtvw = (TextView)view.findViewById(R.id.Seconds);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hourTxtvw.setText("00:");
                minTxtvw.setText("00:");
                secTxtvw.setText("00");
                counter = 0;
                button.setText("Start");
                handler.removeCallbacks(timerRunnable);
            }
        });
        button.setText("Start");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().equals("Start")) {
                    button.setText("Stop");
                    handler.postDelayed(timerRunnable,0);
                } else {
                    button.setText("Start");
                    handler.removeCallbacks(timerRunnable);
                }
            }
        });
        return view;
    }
}