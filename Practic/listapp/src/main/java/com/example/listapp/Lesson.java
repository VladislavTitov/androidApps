package com.example.listapp;

/**
 * Created by Vladislav on 17.08.2016.
 */
public class Lesson {
    private String mName;
    private String mTime;
    private String mRoom;
    private String mTeacher;

    public Lesson(String mName, String mTime, String mRoom, String mTeacher) {
        this.mName = mName;
        this.mTime = mTime;
        this.mRoom = mRoom;
        this.mTeacher = mTeacher;
    }

    public String getmName() {
        return mName;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmRoom() {
        return mRoom;
    }

    public String getmTeacher() {
        return mTeacher;
    }
}
