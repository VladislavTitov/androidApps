package com.example.vladislav.control2.observe;

import com.example.vladislav.control2.models.Note;

import java.util.ArrayList;

public interface Observer {
    void update(ArrayList<Note> notes);
}
