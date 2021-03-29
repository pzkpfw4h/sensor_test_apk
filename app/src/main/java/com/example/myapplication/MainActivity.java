package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor pressure;
    private Sensor light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //null
        //ambiente_temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

    }

    /**
     * Called when the accuracy of the sensor changes.
     * @param sensor {@inheritDoc}
     * @param accuracy {@inheritDoc}
     */
    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    /**
     * Called when there is a change in the sensor value.
     * @param event {@inheritDoc}
     */
    @Override
    public final void onSensorChanged(SensorEvent event) {

       if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            TextView output = findViewById(R.id.ambient_temperature);
            output.setText(String.format("%.4f",event.values[0]) + "lux");
        }
        if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
            TextView output = findViewById(R.id.pressure);
            output.setText(String.format("%.4f",event.values[0]) + "hPa");
        }


    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, pressure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}