package com.example.vladislav.control2.providers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vladislav.control2.models.Note;
import com.example.vladislav.control2.observe.Observable;
import com.example.vladislav.control2.observe.Observer;

import java.util.ArrayList;
import java.util.Map;

public class NotesProvider implements Observable{

    public static final String PREFS_NAME = "notesprefs";

    private ArrayList<Note> notesList;

    private ArrayList<Observer> observers;

    private static NotesProvider ourInstance = new NotesProvider();

    public static NotesProvider getInstance() {
        return ourInstance;
    }

    private NotesProvider() {
        notesList = new ArrayList<>();
        observers = new ArrayList<>(1);
    }

    public void restoreNotes(Context context){
        notesList.clear();
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        Map<String, String> map = (Map<String, String>) preferences.getAll();

        for (Map.Entry<String, String> entry: map.entrySet()) {
            notesList.add(new Note(entry.getKey(), entry.getValue()));
        }

        notifyObservers();
    }

    public ArrayList<Note> getNotesList() {
        return notesList;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(notesList);
        }
    }

    public void updateNotes(int position, String name, String content){
        notesList.get(position).setName(name);
        notesList.get(position).setContent(content);
        notifyObservers();
    }

    /*public void addNotes(Context context){

        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        for (int i = 0; i < 20; i++) {
            editor.putString("Note " + i, "This is text for note " + i + "! I should type a lot of letter there but mne len!");
        }
        editor.commit();
    }*/
}
