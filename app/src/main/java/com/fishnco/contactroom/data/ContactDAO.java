package com.fishnco.contactroom.data;

/*
 * DAO - Data Access Object
 * Takes care of the CRUD operations
 */

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fishnco.contactroom.model.Contact;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM contact_table")
    void deleteAll();

    @Query("SELECT * FROM contact_table ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();

}
