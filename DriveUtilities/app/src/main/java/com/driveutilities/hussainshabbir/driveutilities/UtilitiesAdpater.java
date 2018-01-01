package com.driveutilities.hussainshabbir.driveutilities;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by HussainShabbir on 12/23/17.
 */

public class UtilitiesAdpater extends BaseAdapter {
    private Context context;
    private String[] details;

    public UtilitiesAdpater(Context context, String[] details) {
        this.context = context;
        this.details = details;
    }
    @Override
    public int getCount() {
        return details.length;
    }

    @Override
    public Object getItem(int position) {
        return details[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.linearlayout_utilities, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.TextView);
        textView.setText(details[position]);
        return convertView;
    }
}
