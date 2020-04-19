package com.valehasadov.roomtest1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

@Dao
public interface PersonDao {

    @Insert
    void insertPerson(Person person);

    @Query("SELECT * FROM _person")
    LiveData<List<Person>> getPeople();

    @Query("SELECT * FROM _person WHERE id=:personId")
    LiveData<List<Person>> getPerson(String personId);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

}
