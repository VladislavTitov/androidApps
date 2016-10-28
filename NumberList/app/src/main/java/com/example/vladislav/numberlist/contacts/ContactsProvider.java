package com.example.vladislav.numberlist.contacts;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactsProvider {

    int count = 0;

    private static final String CONTACTS_PREFERENCES = "contacts_preferences";
    private static final String DELETED_PREFERENCES = "deleted_contacts_prefs";

    public static final String MY_TAG = "mytag";

    private List<Contact> contacts;
    private List<Contact> deletedContacts;

    private static ContactsProvider instance;

    private ContactsProvider() {
        contacts = new ArrayList<>();
        deletedContacts = new ArrayList<>();
    }

    static {
        instance = new ContactsProvider();
    }

    public static ContactsProvider getInstance(){
        return instance;
    }

    public void addNewUser(Context context, String number, String name){
        SharedPreferences prefs = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        contacts.add(new Contact(number, name));
        prefs.edit().putString(number, name).commit();
        Log.d(MY_TAG, number);
        Log.d(MY_TAG, name);
        Log.d(MY_TAG, "" + contacts.size());
        Log.d(MY_TAG, "" + ++count);
    }

    public void deleteUser(Context context, int position){
        if (position<0){
            throw new IllegalArgumentException();
        }
        SharedPreferences prefs = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences delPrefs = context.getSharedPreferences(DELETED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorNormal = prefs.edit();
        SharedPreferences.Editor editorDeleted = delPrefs.edit();

        Contact deletingContact = contacts.get(position);

        if (prefs.contains(deletingContact.getPhoneNumber())){
            editorNormal.remove(deletingContact.getPhoneNumber());
        }
        editorDeleted.putString(deletingContact.getPhoneNumber(), deletingContact.getUsername());

        contacts.remove(position);
        deletedContacts.add(deletingContact);

        editorNormal.commit();
        editorDeleted.commit();
    }

    public void restoreContacts(Context context){
        contacts.clear();
        deletedContacts.clear();
        SharedPreferences prefs = context.getSharedPreferences(CONTACTS_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences delPrefs = context.getSharedPreferences(DELETED_PREFERENCES, Context.MODE_PRIVATE);
        Map<String, String> allContacts = (Map<String, String>) prefs.getAll();
        Map<String, String> allDelContacts = (Map<String, String>) delPrefs.getAll();
        for (Map.Entry<String, String> entry: allContacts.entrySet()){
            contacts.add(new Contact(entry.getKey(), entry.getValue()));
        }
        for (Map.Entry<String, String> entry: allDelContacts.entrySet()){
            deletedContacts.add(new Contact(entry.getKey(), entry.getValue()));
        }
    }


    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Contact> getDeletedContacts() {
        return deletedContacts;
    }
}
