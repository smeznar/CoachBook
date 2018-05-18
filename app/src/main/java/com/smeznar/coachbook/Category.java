package com.smeznar.coachbook;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Category extends RealmObject {
    @Required
    private String mCategoryName;
    private String mCategoryDescription;

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
