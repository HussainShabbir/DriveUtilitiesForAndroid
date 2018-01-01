package com.driveutilities.hussainshabbir.driveutilities;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.MapFragment;


public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setUp();
        if (getIntent().getStringExtra("key").equals("Car Parking")) {
            CarParkingFragment carParkingFragment = new CarParkingFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_Container, carParkingFragment);
            fragmentTransaction.commit();
        } /*else if (getIntent().getStringExtra("key").equals("Parking Meter")) {
            ContainerFragment containerFragment = new ContainerFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_Container, containerFragment);
            fragmentTransaction.commit();
        } else {
            Log.d("Other",getIntent().getStringExtra("key"));

        }*/
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
}
