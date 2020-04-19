package com.valehasadov.roomtest1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

public class EditViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private PersonDatabase database;
    private PersonDao personDao;

    public EditViewModel(@NonNull Application application) {
        super(application);
        database = PersonDatabase.getDatabase(application);
        personDao = database.personDao();
    }

    public LiveData<List<Person>> getPerson(String personId){
        return personDao.getPerson(personId);
    }


}
