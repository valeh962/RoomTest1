package com.valehasadov.roomtest1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

public class EditPersonActivity extends AppCompatActivity {

    public static final String UPDATED_ID = "UPDATED_ID";
    public static final String NEW_NAME = "NEW_NAME";
    public static final String NEW_AGE = "NEW_AGE";

    private TextView personDetails;
    private EditText personName;
    private EditText personAge;
    private Button editButton;
    private EditViewModel viewModel;
    private Bundle bundle;
    private String personId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        personDetails = findViewById(R.id.person_details);
        personName = findViewById(R.id.person_name);
        personAge = findViewById(R.id.person_age);
        editButton = findViewById(R.id.edit);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            personId = bundle.getString("person_id");
        }

        editButton.setOnClickListener(v -> {
            {
                if (TextUtils.isEmpty(personName.getText())
                        || TextUtils.isEmpty(personAge.getText())) {
                    setResult(RESULT_CANCELED);
                    finish();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(UPDATED_ID, personId);
                    resultIntent.putExtra(NEW_NAME, personName.getText().toString());
                    resultIntent.putExtra(NEW_AGE, personAge.getText().toString());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        viewModel = ViewModelProviders.of(this).get(EditViewModel.class);
        if (personId != null && personId.length() > 0) {
            viewModel.getPerson(personId).observe(this, people ->
                    personDetails.setText(
                            String.format(
                                    "%s (%s)"
                                    , people.get(0).getName()
                                    , people.get(0).getAge())));
        }

    }
}
