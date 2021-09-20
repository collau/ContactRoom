package com.fishnco.contactroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fishnco.contactroom.model.Contact;
import com.fishnco.contactroom.model.ContactViewModel;
import com.google.android.material.snackbar.Snackbar;

public class NewContact extends AppCompatActivity {
    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION_REPLY = "occupation_reply";
    private EditText editText_name;
    private EditText editText_occupation;
    private Button buttonSave;
    private Button buttonUpdate;
    private Button buttonDelete;
    private int contactId = 0;
    private Boolean isEdit = false;

    //Look how easy it is now
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        editText_name = findViewById(R.id.editText_contactName);
        editText_occupation = findViewById(R.id.editText_occupation);
        buttonSave = findViewById(R.id.button_save);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        if (getIntent().hasExtra(MainActivity.CONTACT_ID)) {
            contactId = getIntent().getIntExtra(MainActivity.CONTACT_ID, 0);

            contactViewModel.get(contactId).observe(this, new Observer<Contact>() {
                @Override
                public void onChanged(Contact contact) {
                    if (contact != null) {
                        editText_name.setText(contact.getName());
                        editText_occupation.setText(contact.getOccupation());
                    }
                }
            });
            isEdit = true;
        }

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();

                if (!TextUtils.isEmpty(editText_name.getText()) && !TextUtils.isEmpty(editText_occupation.getText())) {
                    String name = editText_name.getText().toString();
                    String occupation = editText_occupation.getText().toString();

                    replyIntent.putExtra(NAME_REPLY, name);
                    replyIntent.putExtra(OCCUPATION_REPLY, occupation);
                    setResult(RESULT_OK, replyIntent);

//                    Contact contact = new Contact(editText_name.getText().toString(), editText_occupation.getText().toString());


                    //insert method is a static method, just use the actual class
//                    ContactViewModel.insert(contact);
                } else {
                    setResult(RESULT_CANCELED, replyIntent);
//                    Toast.makeText(NewContact.this, R.string.empty, Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });

        //Update button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = contactId;
                String name = editText_name.getText().toString().trim();
                String occupation = editText_occupation.getText().toString().trim();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(occupation)) {
                    Snackbar.make(editText_name, R.string.empty, Snackbar.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact();
                    contact.setId(id);
                    contact.setName(name);
                    contact.setOccupation(occupation);
                    ContactViewModel.update(contact);
                    finish();
                }
            }
        });

        if (isEdit) {
            buttonSave.setVisibility(View.GONE);
        } else {
            buttonUpdate.setVisibility(View.GONE);
            buttonDelete.setVisibility(View.GONE);
        }
    }
}