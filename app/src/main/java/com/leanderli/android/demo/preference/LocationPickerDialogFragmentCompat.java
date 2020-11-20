package com.leanderli.android.demo.preference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.leanderli.android.demo.R;

public class LocationPickerDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    private final static String TAG = "LocationPickerDialogFragmentCompat";
    private final static String PREF_LOCATION_ID = "location_id";

    private int locationId;

    public static  LocationPickerDialogFragmentCompat newInstance(String key) {
        final  LocationPickerDialogFragmentCompat fragmentCompat = new  LocationPickerDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragmentCompat.setArguments(b);
        return fragmentCompat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            locationId = savedInstanceState.getInt(PREF_LOCATION_ID);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PREF_LOCATION_ID, locationId);
    }

    @Override
    protected View onCreateDialogView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.preference_time_picker, null);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {

        }
    }

    public Preference getLocationPickerPreference() {
        return ( LocationPickerPreference) getPreference();
    }
}
