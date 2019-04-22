package com.example.networksimulation;

public class Command {

    private int mId;

    private String mTitle;

    public Command() {
    }

    public Command(int id, String title) {
        mId = id;
        mTitle = title;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
