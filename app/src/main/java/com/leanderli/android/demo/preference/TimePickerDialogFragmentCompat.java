package com.leanderli.android.demo.preference;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.leanderli.android.demo.R;

public class TimePickerDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    public static final String FRAGMENT_TAG = "Time_picker_dialog";
    private static final String SAVE_STATE_TIME = "TimePickerDialogFragmentCompat.time";

    private TimePicker picker;
    private int timeInMinute;

    public static TimePickerDialogFragmentCompat newInstance(String key) {
        final  TimePickerDialogFragmentCompat
                f = new  TimePickerDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            timeInMinute = getTimePickerPreference().getTime();
        } else {
            timeInMinute = savedInstanceState.getInt(SAVE_STATE_TIME);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_STATE_TIME, calCurrentTime());
    }

    @Override
    protected View onCreateDialogView(Context context) {
        return LayoutInflater.from(context).inflate(R.layout.preference_time_picker, null);

    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        picker = view.findViewById(R.id.time_picker);
        picker.setIs24HourView(true);

        picker.setCurrentHour(timeInMinute / 60);
        picker.setCurrentMinute(timeInMinute % 60);
    }

    private TimePickerPreference getTimePickerPreference() {
        return (TimePickerPreference) getPreference();
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            final TimePickerPreference preference = getTimePickerPreference();
            timeInMinute = calCurrentTime();
            if (preference.callChangeListener(timeInMinute)) {
                preference.setTime(timeInMinute);
            }
        }
    }

    private int calCurrentTime() {
        if (picker == null) return 0;
        return picker.getCurrentHour() * 60 + picker.getCurrentMinute();
    }
}