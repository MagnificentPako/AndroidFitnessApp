package de.rub.cs.selab22.a14;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import static de.rub.cs.selab22.a14.helper.IsSameData.isSameData;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import de.rub.cs.selab22.a14.database.AppDatabase;
import de.rub.cs.selab22.a14.database.DataPoint;
import de.rub.cs.selab22.a14.database.Identifier;
import de.rub.cs.selab22.a14.database.daos.DataDao;
import de.rub.cs.selab22.a14.database.entities.Data;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private DataDao dataDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        dataDao = db.dataDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    /**
     * Test if storing {@link Data} in Room database
     * works as expected and stays the same when retrieved.
     *
     * @throws Exception
     */
    @Test
    public void writeDataAndReadInList() throws Exception {
        Data data = new Data(new DataPoint("Test", "Data"), Identifier.STEP_COUNTER);
        dataDao.insertAll(data);
        List<Data> bySensor = dataDao.getByIdentifier("TEST");
        assertThat(bySensor.get(0), isSameData(data));
    }

}
