package com.fishnco.contactroom.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fishnco.contactroom.data.ContactDAO;
import com.fishnco.contactroom.model.Contact;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactRoomDatabase extends RoomDatabase {

    public abstract ContactDAO contactDAO();
    public static final int NUMBER_OF_THREADS = 4;

    //Singleton
    //volatile -- instance will remove itself if need be
    private static volatile ContactRoomDatabase INSTANCE;

    //Executor service - run methods in background threads
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ContactRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContactRoomDatabase.class, "contact_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            ContactDAO contactDAO = INSTANCE.contactDAO();
                            contactDAO.deleteAll();

                            Contact contact = new Contact("Jeffrey", "Engineer");
                            contactDAO.insert(contact);

                            contact = new Contact("James", "Spy");
                            contactDAO.insert(contact);
                        }
                    });
                }
            };
}
