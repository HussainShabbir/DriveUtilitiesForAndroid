package com.driveutilities.hussainshabbir.driveutilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by HussainShabbir on 12/29/17.
 */

public class CarParkingAdapter extends RecyclerView.Adapter<CarParkingAdapter.MyViewHolder> {
    private List<CarParking> dataSourceList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView location;
        public Button deleteButton;
        public MyViewHolder(View view) {
            super(view);
            this.location = (TextView)view.findViewById(R.id.textview);
            this.deleteButton = (Button)view.findViewById(R.id.delete);
        }
    }
    public CarParkingAdapter(List<CarParking> dataSourceList, Context context) {
        this.dataSourceList = dataSourceList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carparkingrow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        CarParking carParking = dataSourceList.get(position);
        Log.d("Value is ",carParking.getLocation());
        holder.location.setText(carParking.getLocation());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSourceList.remove(position);
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<CarParking>>() {}.getType();
                String json = gson.toJson(dataSourceList,listType);
                editor.putString("list",json);
                editor.commit();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSourceList.size();
    }
}
