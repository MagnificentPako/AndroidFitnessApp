package de.rub.cs.selab22.a14.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.entities.Data;

/**
 * The Data Access Object used to access {@link Data} objects from the
 * {@link de.rub.cs.selab22.a14.database.AppDatabase}.
 */
@Dao
public interface DataDao {
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Insert
    void insertAll(Data... data);

    @Update
    void updateAll(Data... data);

    @Delete
    void delete(Data data);

    @Query("SELECT * FROM data WHERE timestamp >= :since")
    List<Data> getSince(Date since);

    @Query("SELECT * FROM data WHERE timestamp BETWEEN :since AND :until")
    List<Data> getBetween(Date since, Date until);

    @Query("SELECT * FROM data WHERE sensor_identifier LIKE :identifier AND " +
            "timestamp >= :since")
    List<Data> getSinceByIdentifier(Date since, Identifier identifier);

    @Query("SELECT * FROM data WHERE sensor_identifier LIKE :identifier AND " +
            "timestamp BETWEEN :since AND :until")
    List<Data> getBetweenByIdentifier(Date since, Date until, Identifier identifier);

    @Query("SELECT * FROM data WHERE sensor_identifier LIKE :identifier")
    List<Data> getByIdentifier(Identifier identifier);

    @Query("DELETE FROM data")
    public void nukeTable();
}
