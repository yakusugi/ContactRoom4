package com.example.contactroom4.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.contactroom4.model.Contact;
import com.example.contactroom4.util.ContactRoomDatabase;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<Contact>> allContacts;

    public ContactRepository(Application application) {
        ContactRoomDatabase db = ContactRoomDatabase.getDatabase(application);
        contactDao = db.contactDao();

        allContacts = contactDao.getAllContacts();
    }

    public LiveData<List<Contact>> getAllData() { return allContacts; }
    public void insert(Contact contact) {
        ContactRoomDatabase.dataWritableExecutor.execute(() -> {
            contactDao.insert(contact);
        });
    }

    public LiveData<Contact> get(int id) {
        return contactDao.get(id);
    }

    public void update(Contact contact) {
        ContactRoomDatabase.dataWritableExecutor.execute(() -> contactDao.update(contact));
    }

    public void delete(Contact contact) {
        ContactRoomDatabase.dataWritableExecutor.execute(() -> contactDao.delete(contact));
    }

}
