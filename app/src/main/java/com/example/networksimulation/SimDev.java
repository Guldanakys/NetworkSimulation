package com.example.networksimulation;

public class SimDev {

    private int mId;

    private String mName;

    private int mImage;

    private String mIpAddress;

    public SimDev() {
    }

    public SimDev(int id, String name, int image, String ipAddress) {
        mId = id;
        mName = name;
        mImage = image;
        mIpAddress = ipAddress;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getIpAddress() {
        return mIpAddress;
    }

    public void setIpAddress(String ipAddress) {
        mIpAddress = ipAddress;
    }
}
