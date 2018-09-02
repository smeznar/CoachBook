package com.smeznar.coachbook.API;

import android.util.Log;

import com.smeznar.coachbook.models.Category;
import com.smeznar.coachbook.models.Exercise;
import com.smeznar.coachbook.models.Exercise_Category;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class ExerciseApi {
    private Realm mRealmDatabase;

    public ExerciseApi(Realm realm){
        mRealmDatabase = realm;
    }

    public void setDatabase(Realm realm){
        mRealmDatabase = realm;
    }

    public Category createCategory(String name, String description){
        try{
            mRealmDatabase.beginTransaction();
            Category category = mRealmDatabase.createObject(Category.class,name);
            category.setmCategoryDescription(description);
            mRealmDatabase.commitTransaction();
            return category;
        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return null;
        }
    }

    public Exercise createExercise(final String name, final String description, final int level,
                                   final List<Category> categoryArray){
        Exercise exercise;
        try {
            mRealmDatabase.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
                @Override
                public void execute(Realm realm) {
                    Number currentIdNum = realm.where(Exercise.class).max("mId");
                    int nextId;
                    if(currentIdNum == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNum.intValue() + 1;
                    }
                    Exercise eToken = new Exercise(nextId,name,description,level);
                    realm.insertOrUpdate(eToken);
                    for (int i = 0; i < categoryArray.size(); i++) {
                        Exercise_Category ecToken = new Exercise_Category(nextId,categoryArray.get(i).getmCategoryName());
                        realm.insertOrUpdate(ecToken);
                    }
                }
            });
            exercise = mRealmDatabase.where(Exercise.class).equalTo("mName",name)
                    .equalTo("mDescription",description).equalTo("mLevel",level).findFirst();
            return exercise;
        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return null;
        }
    }

    public List<Category> getCategories(){
        try {
            RealmResults<Category> categoryResults = mRealmDatabase.where(Category.class).findAll().sort("mCategoryName", Sort.ASCENDING);
            return new ArrayList<>(mRealmDatabase.copyFromRealm(categoryResults));
        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return null;
        }
    }
}
