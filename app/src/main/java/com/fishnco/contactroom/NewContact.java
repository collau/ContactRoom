package com.fishnco.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fishnco.contactroom.model.Contact;
import com.fishnco.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {
    private EditText editText_name;
    private EditText editText_occupation;
    private Button buttonSave;

    //Look how easy it is now
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        editText_name = findViewById(R.id.editText_contactName);
        editText_occupation = findViewById(R.id.editText_occupation);
        buttonSave = findViewById(R.id.button_save);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(editText_name.getText()) && !TextUtils.isEmpty(editText_occupation.getText())) {
                    Contact contact = new Contact(editText_name.getText().toString(), editText_occupation.getText().toString());

                    //insert method is a static method, just use the actual class
                    ContactViewModel.insert(contact);
                } else {
                    Toast.makeText(NewContact.this, R.string.empty, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}