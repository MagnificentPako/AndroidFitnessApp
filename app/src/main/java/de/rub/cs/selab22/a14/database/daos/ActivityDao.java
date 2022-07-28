package de.rub.cs.selab22.a14.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import de.rub.cs.selab22.a14.database.entities.DBActivity;
import de.rub.cs.selab22.a14.database.entities.Data;

@Dao
public interface ActivityDao {

    @Query("SELECT * FROM activity")
    List<DBActivity> getAll();

    @Query("SELECT * FROM activity WHERE uid LIKE :id")
    DBActivity getById(int id);

    @Insert
    void insertAll(DBActivity... data);

    @Update
    void updateAll(DBActivity... data);

    @Delete
    void delete(DBActivity data);

}
