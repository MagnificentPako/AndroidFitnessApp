package de.rub.cs.selab22.a14.sensors;

import de.rub.cs.selab22.a14.database.DBHelper;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.Data;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorCenter implements SensorEventListener {

    protected long steps;
    protected long recordedSteps = 0;
    float[] accelValue = new float[3];
    private final Context context;
    private Sensor stepCounter;
    private Sensor accelerometer;

    public SensorCenter(Context context) {
        this.context = context;
        SensorManager sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stepChange(SensorEvent event) {
        int deviceSteps = (int) event.values[0];
        if (recordedSteps < 1) {
            recordedSteps = (int) event.values[0];
        }
        steps = deviceSteps - recordedSteps;

        DataDao dataDao = DBHelper.INSTANCE.getDataDao();
        Data accelerometerData = new Data( new DataPoint(steps),
                Identifier.STEP_COUNTER);
        dataDao.insertAll(accelerometerData);
    }

    public void accelChange(SensorEvent event) {
        accelValue[0] = Math.abs(event.values[0]);
        accelValue[1] = Math.abs(event.values[1]);
        accelValue[2] = Math.abs(event.values[2]);

        DataDao dataDao = DBHelper.INSTANCE.getDataDao();
        Data accelerometerRawData = new Data( new DataPoint(accelValue[0], accelValue[1], accelValue[2]),
                Identifier.ACCELEROMETER);
        Data accelerometerVectorData= new Data( new DataPoint(getVectorLength(accelValue[0], accelValue[1], accelValue[2])),
                Identifier.ACCELEROMETER_VECTOR_LENGTH);
        dataDao.insertAll(accelerometerRawData);
        dataDao.insertAll(accelerometerVectorData);
    }

    public float getVectorLength(float x, float y, float z) {
        float vectorLength = (float) Math.sqrt(x*x + y*y + z*z);
        return vectorLength;
    }

    public void resetStepCounter(){
        steps = 0;
        recordedSteps = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                this.stepChange(event);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                this.accelChange(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}