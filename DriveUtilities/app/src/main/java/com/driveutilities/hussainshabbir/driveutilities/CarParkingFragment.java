package com.driveutilities.hussainshabbir.driveutilities;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONStringer;

import java.io.Serializable;
import java.lang.reflect.Type;
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
    final private static String SHARED_PREFS_FILE = "prefs";
    private Gson gson = new Gson();
    private Type listType = new TypeToken<List<CarParking>>() {}.getType();
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_car_parking, container, false);
        RecyclerView.LayoutManager ll = new LinearLayoutManager(getContext());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String jsonString = sharedPreferences.getString("list","");
        if (!jsonString .isEmpty()) {
            dataSourceList = gson.fromJson(jsonString, listType);
        }
        carParkingAdapter = new CarParkingAdapter(dataSourceList, getActivity());
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(ll);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(carParkingAdapter);
        final Button addBtn = (Button)view.findViewById(R.id.addButton);
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
                    final EditText editText = (EditText)customPopupLayout.findViewById(R.id.editText);
                    editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            addBtn.setVisibility((hasFocus) ? View.GONE: View.VISIBLE);
                        }
                    });
                    popupWindow.setFocusable(true);
                    popupWindow.update();
                    Button okBtn = (Button)customPopupLayout.findViewById(R.id.okButton);
                    okBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editText.getText().toString().trim().equals("")) {
                                Toast.makeText(getContext(),"Location cannot be empty",100).show();
                                return;
                            } else {
                                popupWindow.dismiss();
                                dataSourceList.add(new CarParking(editText.getText().toString()));
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String json = gson.toJson(dataSourceList,listType);
                                editor.putString("list",json);
                                editor.commit();
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
