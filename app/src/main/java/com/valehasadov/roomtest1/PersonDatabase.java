package com.valehasadov.roomtest1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

@Database(entities = {Person.class}, version = 1)
public abstract class PersonDatabase extends RoomDatabase {

    private static volatile PersonDatabase personDatabase;

    static PersonDatabase getDatabase(Context context) {
        if (personDatabase == null) {
            synchronized (PersonDatabase.class) {
                if (personDatabase == null) {
                    personDatabase = Room.databaseBuilder(context, PersonDatabase.class, "my_person_database").build();
                }
            }
        }
        return personDatabase;
    }

    public abstract PersonDao personDao();

}
