package com.fishnco.contactroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class NewContact extends AppCompatActivity {
    private EditText editText_name;
    private EditText editText_occupation;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        editText_name = findViewById(R.id.editText_contactName);
        editText_occupation = findViewById(R.id.editText_occupation);
        buttonSave = findViewById(R.id.button_save);
    }
}