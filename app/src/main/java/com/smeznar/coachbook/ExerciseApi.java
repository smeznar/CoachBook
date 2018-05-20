package com.smeznar.coachbook;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.smeznar.coachbook.models.Category;
import com.smeznar.coachbook.models.Exercise;
import com.smeznar.coachbook.models.Exercise_Category;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

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

    public Exercise createExercise(final String name, final String description, final int level,
                                   final ArrayList<String> categoryArray, final ArrayList<Bitmap> bitmapArray){
        Exercise exercise;
        try {
            mRealmDatabase.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
                @Override
                public void execute(Realm realm) {
                    // increment index
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
                        Exercise_Category ecToken = new Exercise_Category(nextId,categoryArray.get(i));
                        realm.insertOrUpdate(ecToken);
                    }
                    for (int i = 0; i < bitmapArray.size(); i++) {
                        createPicture(bitmapArray.get(i),i,nextId);
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

    public boolean createPicture(Bitmap bitmap,int order,long exerciseId){
        return true;
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
