package com.smeznar.coachbook;

import android.content.Context;
import android.util.Log;

import com.smeznar.coachbook.models.Category;
import com.smeznar.coachbook.models.Exercise;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ExerciseApi {
    private Realm mRealmDatabase;
    private Context mContext;

    public ExerciseApi(Context context){
        mContext = context;
        RealmConfiguration config = new RealmConfiguration.Builder().name("exercise.realm").build();
        mRealmDatabase = Realm.getInstance(config);
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

    public Exercise createExercise(){
        return null;
    }

    public boolean onClose(){
        try {
            mRealmDatabase.close();
        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return false;
        }
        return true;
    }
}
