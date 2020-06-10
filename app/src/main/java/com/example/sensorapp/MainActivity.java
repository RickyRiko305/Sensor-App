package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    private TextView accelerometerdata;
    private TextView gyroscopedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);

        accelerometerdata = (TextView) findViewById(R.id.accelerometer);
        gyroscopedata = (TextView) findViewById(R.id.gyroscope);


        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                String val="accelerometer\n";
                val += " x: " + tx;
                val += "\n y: " + ty;
                val += "\n z: " + tz;

                accelerometerdata.setText(val);
            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotate(float rx, float ry, float rz) {
                String val="gyroscope\n";
                val += " x: " + rx;
                val += "\n y: " + ry;
                val += "\n z: " + rz;

                gyroscopedata.setText(val);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
    }
}
