package com.valehasadov.roomtest1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

public class NewPersonActivity extends AppCompatActivity {

    public static final String PERSON_NAME = "PERSON_NAME";
    public static final String PERSON_AGE = "PERSON_AGE";
    private EditText personName;
    private EditText personAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        personName = findViewById(R.id.person_name);
        personAge = findViewById(R.id.person_age);
        Button addBtn = findViewById(R.id.add);

        addBtn.setOnClickListener(v -> {
            {
                Intent resultIntent = new Intent();
                if (TextUtils.isEmpty(personAge.getText()) && TextUtils.isEmpty(personName.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    resultIntent.putExtra(PERSON_NAME, personName.getText().toString());
                    resultIntent.putExtra(PERSON_AGE, personAge.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });


    }
}
