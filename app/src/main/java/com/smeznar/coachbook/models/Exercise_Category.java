package com.smeznar.coachbook.models;

import io.realm.RealmObject;

public class Exercise_Category extends RealmObject {
    private long mExerciseId;
    private String mCategoryName;

    public Exercise_Category(long mExerciseId, String mCategoryName) {
        this.mExerciseId = mExerciseId;
        this.mCategoryName = mCategoryName;
    }

    public Exercise_Category(){
    }

    public long getmExerciseId() {
        return mExerciseId;
    }

    public void setmExerciseId(long mExerciseId) {
        this.mExerciseId = mExerciseId;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }
}
