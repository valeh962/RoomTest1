package com.valehasadov.roomtest1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;
import java.util.UUID;

import static com.valehasadov.roomtest1.EditPersonActivity.NEW_AGE;
import static com.valehasadov.roomtest1.EditPersonActivity.NEW_NAME;
import static com.valehasadov.roomtest1.EditPersonActivity.UPDATED_ID;
import static com.valehasadov.roomtest1.NewPersonActivity.PERSON_AGE;
import static com.valehasadov.roomtest1.NewPersonActivity.PERSON_NAME;

public class MainActivity extends AppCompatActivity implements PersonRecyclerAdapter.OnDeleteClickListener {

    public static final int ADD_PERSON_REQUEST_CODE = 111;
    public static final int EDIT_PERSON_REQUEST_CODE = 112;
    private String TAG = this.getClass().getSimpleName();
    private PersonViewModel viewModel;
    private Button addPerson;
    private RecyclerView personRecycler;
    private PersonRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPerson = findViewById(R.id.add_person);
        personRecycler = findViewById(R.id.person_recycler);
        personRecycler.setLayoutManager(new LinearLayoutManager(this));
        personRecycler.setAdapter(adapter = new PersonRecyclerAdapter(this, this));


        addPerson.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, NewPersonActivity.class),
                    ADD_PERSON_REQUEST_CODE);
        });

        viewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        viewModel.getPeople().observe(this, people -> adapter.setPeople(people));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case ADD_PERSON_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    String id = UUID.randomUUID().toString();
                    Person person;
                    if (data != null) {
                        person = new Person(id,
                                Objects.requireNonNull(data.getStringExtra(PERSON_NAME)),
                                Objects.requireNonNull(data.getStringExtra(PERSON_AGE)));
                        viewModel.insertPerson(person);
                        Toast.makeText(getApplicationContext(), "Person saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Person not saved", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case EDIT_PERSON_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Person person;
                    if (data != null) {
                        String id = data.getStringExtra(UPDATED_ID);
                        String name = data.getStringExtra(NEW_NAME);
                        String age = data.getStringExtra(NEW_AGE);
                        if (id != null && name != null && age != null) {
                            person = new Person(id, name, age);
                            viewModel.updatePerson(person);
                            Toast.makeText(getApplicationContext(), "Person updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Person not updated", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }


    }

    @Override
    public void onDeleteClicked(Person person) {
        viewModel.deletePerson(person);
    }
}
