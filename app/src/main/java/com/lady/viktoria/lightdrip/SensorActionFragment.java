package com.lady.viktoria.lightdrip;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lady.viktoria.lightdrip.DatabaseModels.SensorData;
import com.lady.viktoria.lightdrip.RealmConfig.RealmBaseFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

import static io.realm.Realm.getInstance;

public class SensorActionFragment extends RealmBaseFragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private final static String TAG = SensorActionFragment.class.getSimpleName();

    public SensorActionFragment() {
    }

    Calendar SensorStart;
    int mYear, mMonthOfYear, mDayOfMonth, mHourOfDay, mMinute;
    private Realm mRealm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sensoraction, container, false);
        SensorStart = Calendar.getInstance();
        Realm.init(getActivity());
        mRealm = getInstance(getRealmConfig());
        SensorDialog();
        return view;
    }

    protected void SensorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please choice if you want to stop current Sensor or start a new Sensor");
        builder.setTitle("Sensor Actions");
        builder.setPositiveButton("Start Sensor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!isSensorActive()) {
                    StartSensor();
                    dialog.dismiss();
                } else {
                    Snackbar.make(getView(), "Please stop current Sensor fist!", Snackbar.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        builder.setNegativeButton("Stop sensor", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StopSensor();
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void StartSensor() {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    SensorActionFragment.this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            );
            dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    private void StopSensor() {
        long stopped_at = new Date().getTime();
        RealmResults<SensorData> results = mRealm.where(SensorData.class).findAll();
        String lastUUID = results.last().getuuid();
        SensorData mSensorData = mRealm.where(SensorData.class).equalTo("uuid", lastUUID).findFirst();
        mRealm.beginTransaction();
        mSensorData.setstopped_at(stopped_at);
        mRealm.commitTransaction();
        mRealm.close();
    }

    private void CurrentSensor() {

    }

    private boolean isSensorActive() {
        RealmResults<SensorData> results = mRealm.where(SensorData.class).findAll();
        String lastUUID = results.last().getuuid();
        SensorData mSensorData = mRealm.where(SensorData.class).equalTo("uuid", lastUUID).findFirst();
        mRealm.beginTransaction();
        mRealm.commitTransaction();
        mRealm.close();
        if (mSensorData.getstopped_at() == 0L) {
            return true;
        }
        return false;
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonthOfYear = monthOfYear;
        mDayOfMonth = dayOfMonth;
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                SensorActionFragment.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mHourOfDay = Integer.parseInt(hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay);
        mMinute = Integer.parseInt(minute < 10 ? "0"+minute : ""+minute);
        SensorStart.set(mYear, mMonthOfYear, mDayOfMonth, mHourOfDay, mMinute, 0);
        long startTime = SensorStart.getTime().getTime();
        String uuid = UUID.randomUUID().toString();
        mRealm.beginTransaction();
        SensorData mSensorData = mRealm.createObject(SensorData.class, uuid);
        mSensorData.setstarted_at(startTime);
        mRealm.commitTransaction();
        mRealm.close();
        getActivity().getFragmentManager().popBackStack();
    }
}