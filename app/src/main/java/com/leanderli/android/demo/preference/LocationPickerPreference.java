package com.leanderli.android.demo.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.preference.DialogPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.preference.PreferenceViewHolder;

import com.leanderli.android.demo.R;

public class LocationPickerPreference extends DialogPreference implements PreferenceManager.OnDisplayPreferenceDialogListener {

    public LocationPickerPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public LocationPickerPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public LocationPickerPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LocationPickerPreference(Context context) {
        this(context, null);
    }

    @Override
    public void setLayoutResource(int layoutResId) {
        super.setLayoutResource(R.layout.preference_location_picker);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View itemView = holder.itemView;


    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        preference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                return false;
            }
        });
    }
}
