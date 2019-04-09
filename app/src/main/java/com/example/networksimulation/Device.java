package com.example.networksimulation;

public class Device {

    private int mId;

    private String mName;

    private int mLayer;

    private int mImageId;

    public Device() {
    }

    public Device(int id, String name, int layer, int imageId) {
        mId = id;
        mName = name;
        mLayer = layer;
        mImageId = imageId;
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

    public int getLayer() {
        return mLayer;
    }

    public void setLayer(int layer) {
        mLayer = layer;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int imageId) {
        mImageId = imageId;
    }

    @Override
    public String toString() {
        return "Layer " + mLayer + " device";
    }
}
