package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.smeznar.coachbook.API.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.models.Category;
import com.smeznar.coachbook.models.Exercise;

import java.util.List;

public class CreateExerciseFragment extends Fragment {

    private ExerciseApi mApi;
    private List<Category> selectedCategories;

    private Button mBtnSelectCategories;
    private Button mBtnNext;
    private Button mBntBack;
    private EditText mEtExerciseDescription;
    private EditText mEtName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_exercise, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getApi();

        mEtName = view.findViewById(R.id.exercise_name);
        mEtExerciseDescription = view.findViewById(R.id.exercise_description);
        mBtnSelectCategories = view.findViewById(R.id.btn_select_exercise_categories);
        mBtnSelectCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCategories();
            }
        });

        mBntBack = view.findViewById(R.id.btn_back);
        mBntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        mBtnNext = view.findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEtName.getText().toString().trim();
                String description = mEtExerciseDescription.getText().toString().trim();
                Exercise exercise = mApi.createExercise(name,description,3,selectedCategories);
                try{
                    Log.d(getTag(),exercise.getmName() + " -> " + exercise.getmDescription());
                } catch (Exception e){
                    Log.e("ERROR CreateExercise", e.getMessage());
                }
                getActivity().onBackPressed();
            }
        });
    }

    private void selectCategories() {
        try {
            List<Category> names;
            names = mApi.getCategories();
            final SelectExerciseCategoriesDialog exerciseCategoriesDialog = new SelectExerciseCategoriesDialog(getActivity(), names);
            exerciseCategoriesDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCategories = exerciseCategoriesDialog.getmCategoryList();
                    exerciseCategoriesDialog.dismiss();
                }
            });
            exerciseCategoriesDialog.show();
        } catch (Exception e) {
            Log.e("ERROR CreateExercise", e.getMessage());
        }
    }

    private void getApi() {
        try {
            MainActivity mainActivity = (MainActivity) getActivity();
            mApi = mainActivity.getApi();
        } catch (Exception e) {
            Log.e("ERROR CreateExercise", e.getMessage());
        }
    }
}