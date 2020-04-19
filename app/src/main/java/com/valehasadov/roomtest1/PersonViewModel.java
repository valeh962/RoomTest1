package com.valehasadov.roomtest1;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

public class PersonViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private PersonDao personDao;
    private PersonDatabase database;
    private LiveData<List<Person>> people;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        database = PersonDatabase.getDatabase(application);
        personDao = database.personDao();
        people = personDao.getPeople();
    }

    public void insertPerson(Person person) {
        new InsertPersonTask(personDao).execute(person);
    }

    public void updatePerson(Person person) {
        new UpdatePersonTask(personDao).execute(person);
    }

    public void deletePerson(Person person) {
        new DeletePersonTask(personDao).execute(person);
    }

    public LiveData<List<Person>> getPeople() {
        return people;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel destroyed");
    }

    private class InsertPersonTask extends AsyncTask<Person, Void, Void> {

        PersonDao personDao;

        InsertPersonTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.insertPerson(people[0]);
            return null;
        }
    }

    private class UpdatePersonTask extends AsyncTask<Person, Void, Void> {

        PersonDao personDao;

        UpdatePersonTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.update(people[0]);
            return null;
        }
    }

    private class DeletePersonTask extends AsyncTask<Person, Void, Void> {

        PersonDao personDao;

        DeletePersonTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDao.delete(people[0]);
            return null;
        }
    }
}
