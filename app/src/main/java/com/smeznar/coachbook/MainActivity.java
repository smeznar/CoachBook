package com.smeznar.coachbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    //private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Realm.init(this.getApplicationContext());
        mRealm = Realm.getDefaultInstance();
        Log.d("sda",mRealm.getPath());

        mRealm.beginTransaction();
        Category category = mRealm.createObject(Category.class);
        category.setmCategoryName("Volleyball");
        mRealm.commitTransaction();
        */
    }

    public void onDestroy() {
        super.onDestroy();
        //mRealm.close();
    }
}
