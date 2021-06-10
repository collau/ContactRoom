package com.fishnco.contactroom.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.fishnco.contactroom.model.Contact;
import com.fishnco.contactroom.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDAO contactDAO;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);

        //Only need access to the DAO for CRUD functions, no need to expose database to repository
        contactDAO = db.contactDAO();

        allContacts = contactDAO.getAllContacts();
    }

    //Getter for allContacts
    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    //To run this function on background thread
    public void insert(final Contact contact) {
        ContactRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });
    }
}
