package com.softdesign.school.data.managers.storage.models;
import android.graphics.drawable.Drawable;

public class ModelUser {

    private String mFirstName;
    private String mLastName;
    private int mRating;
    private Drawable mImage;
    private String mVkLink;
    private String mGithubLink;
    private int mHometask;


    public ModelUser(Drawable mImage, String mFirstName, String mLastName) {
        this.mImage = mImage;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public int getmRating() {
        return mRating;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public String getmGithubLink() {
        return mGithubLink;
    }

    public int getmHometask() {
        return mHometask;
    }
}