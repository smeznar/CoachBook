package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smeznar.coachbook.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.models.Category;

import java.util.List;

public class CreateExerciseFragment extends Fragment {

    private ExerciseApi mApi;
    private List<Category> selectedCategories;

    private Button btnSelectCategories;
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_create_exercise, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getApi();
        btnSelectCategories = view.findViewById(R.id.btn_select_exercise_categories);
        btnSelectCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCategories();
            }
        });
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private void selectCategories(){
        try{
            List<Category> names;
            names = mApi.getCategories();
            final SelectExerciseCategoriesDialog exerciseCategoriesDialog = new SelectExerciseCategoriesDialog(getActivity(), names);
            exerciseCategoriesDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCategories = exerciseCategoriesDialog.getmCategoryList();
                    exerciseCategoriesDialog.dismiss();
                    printCategories();
                }
            });
            exerciseCategoriesDialog.show();
        } catch (Exception e) {
            Log.e("ERROR CreateExercise",e.getMessage());
        }
    }

    private void printCategories(){
        String s = "";
        for (int i = 0; i < selectedCategories.size(); i++) {
            s += selectedCategories.get(i).getmCategoryName() + ", ";
        }
        Log.d("Selected categories",s);
    }

    private void getApi(){
        try {
            MainActivity mainActivity = (MainActivity) getActivity();
            mApi = mainActivity.getApi();
        } catch (Exception e){
            Log.e("ERROR CreateExercise",e.getMessage());
        }
    }
}