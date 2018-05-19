package com.smeznar.coachbook.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Category extends RealmObject {
    @PrimaryKey
    private String mCategoryName;
    private String mCategoryDescription;


    public Category(String mCategoryName, String mCategoryDescription) {
        this.mCategoryName = mCategoryName;
        this.mCategoryDescription = mCategoryDescription;
    }

    public Category() {
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }

    public String getmCategoryDescription() {
        return mCategoryDescription;
    }

    public void setmCategoryDescription(String mCategoryDescription) {
        this.mCategoryDescription = mCategoryDescription;
    }
}
