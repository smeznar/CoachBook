package com.smeznar.coachbook.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Exercise extends RealmObject {
    @PrimaryKey
    private long mId;
    private String mName;
    private String mDescription;
    private int mLevel;

    public Exercise(long mId, String mName, String mDescription, int mLevel) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLevel = mLevel;
    }

    public Exercise(){

    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }
}
