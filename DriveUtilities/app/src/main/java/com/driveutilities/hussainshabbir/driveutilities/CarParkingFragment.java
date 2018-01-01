package com.driveutilities.hussainshabbir.driveutilities;


import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarParkingFragment extends Fragment {
    private List<CarParking> dataSourceList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarParkingAdapter carParkingAdapter;
    private PopupWindow popupWindow;
    private RelativeLayout rl;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_car_parking, container, false);
        RecyclerView.LayoutManager ll = new LinearLayoutManager(getContext());
        carParkingAdapter = new CarParkingAdapter(dataSourceList);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(ll);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(carParkingAdapter);
        Button addBtn = (Button)view.findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null) {
                    final View customPopupLayout = LayoutInflater.from(getContext()).inflate(R.layout.custompopup, null);
                    rl = (RelativeLayout) customPopupLayout.findViewById(R.id.custom_popup_layout);
                    popupWindow = new PopupWindow(customPopupLayout, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    if (Build.VERSION.SDK_INT >= 21) {
                        popupWindow.setElevation(5.0f);
                    }
                    Button okBtn = (Button)customPopupLayout.findViewById(R.id.okButton);
                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            EditText editText = (EditText)customPopupLayout.findViewById(R.id.editText);
                            if (editText.getText().toString().trim().equals("")) {
                                Toast.makeText(getContext(),"Location cannot be empty",500).show();
                                return;
                            } else {
                                popupWindow.dismiss();
                                dataSourceList.add(new CarParking(editText.getText().toString()));
                                carParkingAdapter.notifyDataSetChanged();
                            }

                        }
                    });
                }
                popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
            }
        });
        return view;
    }
}
