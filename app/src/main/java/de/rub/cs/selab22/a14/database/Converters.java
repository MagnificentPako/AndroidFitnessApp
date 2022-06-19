package de.rub.cs.selab22.a14.database;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A collection of {@link TypeConverter} required for certain objects to be properly stored inside
 * the database.
 */
public class Converters {
    @TypeConverter
    public static DataPoint<?> dataPointFromJson(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, DataPoint.class);
    }

    @TypeConverter
    public static String dataPointToJson(DataPoint<?> object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @TypeConverter
    public static Date dateFromLong(long l) {
        return new Date(l);
    }

    @TypeConverter
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
