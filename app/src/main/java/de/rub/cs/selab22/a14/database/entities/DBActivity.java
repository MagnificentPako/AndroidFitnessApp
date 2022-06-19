package de.rub.cs.selab22.a14.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "activity")
public class DBActivity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "start")
    public Date start;

    @ColumnInfo(name = "end")
    public Date end;

    public DBActivity(Date start, Date end) {
        this.start = start;
        this.end = end;
    }
}

