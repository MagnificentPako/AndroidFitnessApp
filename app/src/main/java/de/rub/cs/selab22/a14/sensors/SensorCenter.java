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
import android.widget.Toast;

public class SensorCenter implements SensorEventListener {

    public static SensorCenter INSTANCE;

    protected long steps;
    protected long recordedSteps = 0;
    float[] accelValue = new float[3];
    private final Context context;
    public Sensor stepCounter;
    public Sensor accelerometer;
    private SensorManager sm;
    public static int NORMAL_FREQUENCY = 2000000;
    public static int HIGH_FREQUENCY = 1000000;
    public static int LOW_FREQUENCY = 5000000;

    private SensorCenter(Context context) {
        this.context = context;
        sm = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this, stepCounter, NORMAL_FREQUENCY);
        sm.registerListener(this, accelerometer, NORMAL_FREQUENCY);
    }

    public static void init(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new SensorCenter(context);
        }
    }

    public void unregisterSensor(Sensor s) {
        if(s != null) {
            sm.unregisterListener(this, s);
        }
        else
        {
            Toast.makeText(context.getApplicationContext(), "No Sensor detected!", Toast.LENGTH_LONG).show();
        }
    }

    public void setFrequency(Sensor sensor, int speed) {
        unregisterSensor(sensor);
        if(sensor != null) {
            sm.registerListener(this, sensor, speed);
        }
        /*
        SENSOR_DELAY_FASTEST = 0 microsecond delay
        SENSOR_DELAY_GAME = 20000 microsecond delay
        SENSOR_DELAY_UI = 60000 microsecond delay
        SENSOR_DELAY_NORMAL = 200000 microsecond delay
         */
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
        Data accelerometerRawData = new Data( new DataPoint<Float>(accelValue[0], accelValue[1], accelValue[2]),
                Identifier.ACCELEROMETER);
        Data accelerometerVectorData= new Data( new DataPoint<Float>(getVectorLength(accelValue[0], accelValue[1], accelValue[2])),
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
