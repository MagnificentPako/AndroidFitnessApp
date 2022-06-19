package de.rub.cs.selab22.a14.database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.UUID;

/***
 * Handles storing the User ID uniquely per device (unless the user decides to wipe the application's
 * entire storage). Takes care of storing/retrieving it in/from SharedPreferences and generates a
 * new UUID when required.
 *
 * Example usage:
 *
 * SharedPreferences prefs = getSharedPreferences("22a14", Context.MODE_PRIVATE);
 * UserIdManager.init(prefs);
 * System.out.println(UserIdManager.INSTANCE.getID());
 */
public class UserIdManager {

    public static UserIdManager INSTANCE = null;
    private SharedPreferences prefs;

    private UserIdManager(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public UUID getID() {
        String storedId = prefs.getString("userid", "");
        if(storedId.equals("")) {
            UUID uuid = UUID.randomUUID();
            prefs.edit()
                 .putString("userid", uuid.toString())
                 .commit();
            return uuid;
        } else {
            return UUID.fromString(storedId);
        }
    }

    public static UserIdManager init(SharedPreferences prefs) {
        if(INSTANCE == null) {
            INSTANCE = new UserIdManager(prefs);
        }
        return INSTANCE;
    }

}
