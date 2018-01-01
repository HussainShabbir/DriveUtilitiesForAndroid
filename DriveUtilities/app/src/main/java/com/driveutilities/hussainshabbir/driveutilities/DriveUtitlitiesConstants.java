package com.driveutilities.hussainshabbir.driveutilities;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by HussainShabbir on 12/27/17.
 */

public class DriveUtitlitiesConstants {
    public static final int CarParking = 0;
    public static final int ParkingMeter = 1;
    public static final int GasStations = 2;
    public static final int Hospitals = 3;
    public static final int Restaurants = 4;
    public static final int Hotels = 5;

    @IntDef({CarParking, ParkingMeter, GasStations, Hospitals, Restaurants, Hotels})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Constants {}
    public DriveUtitlitiesConstants(@Constants int constants) {
        System.out.print("Constants :" + constants);
    }
}
