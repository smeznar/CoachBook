package com.smeznar.coachbook.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.smeznar.coachbook.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.models.Exercise;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

//    private ExerciseApi data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        data = new ExerciseApi(this);
//        data.createCategory("Cardio","Cardio exercises");
//        data.onClose();
    }

    public void onDestroy() {
        super.onDestroy();
        //mRealm.close();
    }
}
