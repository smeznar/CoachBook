package com.smeznar.coachbook.API;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.smeznar.coachbook.interfaces.IOnDatabaseChangedListener;
import com.smeznar.coachbook.models.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;

public class DatabaseHelper {

    private Context mContext;
    private Realm mRealmDatabase;
    private RealmConfiguration mRealmConfiguration;
    private FirebaseStorage mFirebaseStorage;
    private IOnDatabaseChangedListener mIOnDatabaseChangedListener;

    private File mCategoriesFile;

    public DatabaseHelper(Context context, IOnDatabaseChangedListener IOnDatabaseChangedListener){
        mContext = context;
        mIOnDatabaseChangedListener = IOnDatabaseChangedListener;
    }

    public Realm getDatabase(){
        if(mRealmDatabase == null){
            mRealmConfiguration = new RealmConfiguration.Builder().name("exercise.realm").build();
            mRealmDatabase = Realm.getInstance(mRealmConfiguration);
        }
        return mRealmDatabase;
    }

    public boolean closeDatabase(){
        try{
            mRealmDatabase.close();
        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return false;
        }
        return true;
    }

    private boolean deleteDatabase(){
        try{
            if(!mRealmDatabase.isClosed()){
                closeDatabase();
            }
            Realm.deleteRealm(mRealmConfiguration);
            mRealmDatabase = Realm.getInstance(mRealmConfiguration);
            mIOnDatabaseChangedListener.onDatabaseChanged(mRealmDatabase);
        } catch (Exception e) {
            Log.e("ERROR ExerciseApi",e.getMessage());
            return false;
        }
        return true;
    }

    public boolean populateDatabase(final Realm.Transaction.OnSuccess onSuccess){
        try {
            deleteDatabase();
            if(mFirebaseStorage == null){
                mFirebaseStorage = FirebaseStorage.getInstance();
            }

            StorageReference storageReference = mFirebaseStorage.getReferenceFromUrl("gs://coachbook-8e158.appspot.com/categories.json");

            mCategoriesFile = new File(mContext.getFilesDir(),"categories.json");
            if(mCategoriesFile.exists()) {
                mCategoriesFile.delete();
            }

            storageReference.getFile(mCategoriesFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    createFromJson(Category.class, mCategoriesFile);
                    onSuccess.onSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("ERROR",exception.getMessage());
                }
            });

        } catch (Exception e){
            Log.e("ERROR ExerciseApi",e.getMessage());
            return false;
        }
        return true;
    }

    private <T extends RealmModel> boolean createFromJson(final Class<T> className, final File json){
        mRealmDatabase.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream inputStream = new FileInputStream(json);
                    mRealmDatabase.createAllFromJson(className, inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

}
