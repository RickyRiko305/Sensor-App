package com.example.sensorapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {

    public interface Listener{
        void onTranslation(float tx, float ty, float tz);
    }

    private Listener listener;

    public void setListener(Listener l){
        listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;


    Accelerometer(Context context){
        sensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(listener != null){
                    listener.onTranslation(sensorEvent.values[0],sensorEvent.values[1],sensorEvent.values[2]);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };
    }

    public void register(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
