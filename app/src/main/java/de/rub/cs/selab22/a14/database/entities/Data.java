package de.rub.cs.selab22.a14.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

import de.rub.cs.selab22.a14.database.DataPoint;

/**
 * Entity storing all data pertaining to loggable data. Associates stored data with a timestamp
 * and a sensor identifier to quickly identify where and when the data was produced.
 *
 * The data itself is stored inside a {@link DataPoint} which is converted to JSON before storage
 * allowing the developer to store arbitrarily complex data as long as it can be handled by
 * {@link com.google.gson.Gson}.
 *
 * Dates are converted to unix timestamps behind the scenes so it is easy to compare them inside
 * the database using regular operators like > and <.
 */
@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "data_point")
    public DataPoint<?> dataPoint;

    @ColumnInfo(name = "sensor_identifier")
    public String sensorIdentifier;

    @ColumnInfo(name = "timestamp")
    public Date timestamp;

    public Data(DataPoint<?> dataPoint, String sensorIdentifier) {
        this.dataPoint = dataPoint;
        this.sensorIdentifier = sensorIdentifier;
        this.timestamp = new Date();
    }
}
