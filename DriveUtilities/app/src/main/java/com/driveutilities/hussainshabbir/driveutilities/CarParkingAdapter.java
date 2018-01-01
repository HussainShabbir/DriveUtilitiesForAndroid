package com.driveutilities.hussainshabbir.driveutilities;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Created by HussainShabbir on 12/29/17.
 */

public class CarParkingAdapter extends RecyclerView.Adapter<CarParkingAdapter.MyViewHolder> {
private List<CarParking> dataSourceList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView location;
        public Button deleteButton;
        public MyViewHolder(View view) {
            super(view);
            this.location = (TextView)view.findViewById(R.id.textview);
            this.deleteButton = (Button)view.findViewById(R.id.delete);
        }
    }
    public CarParkingAdapter(List<CarParking> dataSourceList) {
        this.dataSourceList = dataSourceList;
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
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSourceList.size();
    }
}
