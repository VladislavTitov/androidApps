package com.example.listapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Lesson> lessons = fillLesson();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        LessonAdapter adapter = new LessonAdapter(this, lessons);
        rv.setAdapter(adapter);
    }

    private ArrayList<Lesson> fillLesson(){
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        for (int i = 1; i < 10; i++) {
            Lesson lesson = new Lesson(
                    "Lesson" + i,
                    "time" + i,
                    "room" + (i+100)/4,
                    "teacher" + i
            );
            lessons.add(lesson);
        }
        return lessons;
    }

}
