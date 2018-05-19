package com.smeznar.coachbook.models;

import io.realm.RealmObject;

public class Picture extends RealmObject {
    private int mId;
    private byte[] mPicture;
    private int mOrder;
    private long mExerciseId;

    public Picture() {
    }

    public Picture(int mId, byte[] mPicture, int mOrder, long mExerciseId) {
        this.mId = mId;
        this.mPicture = mPicture;
        this.mOrder = mOrder;
        this.mExerciseId = mExerciseId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public byte[] getmPicture() {
        return mPicture;
    }

    public void setmPicture(byte[] mPicture) {
        this.mPicture = mPicture;
    }

    public int getmOrder() {
        return mOrder;
    }

    public void setmOrder(int mOrder) {
        this.mOrder = mOrder;
    }

    public long getmExerciseId() {
        return mExerciseId;
    }

    public void setmExerciseId(long mExerciseId) {
        this.mExerciseId = mExerciseId;
    }
}
