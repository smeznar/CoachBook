package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.smeznar.coachbook.ExerciseApi;
import com.smeznar.coachbook.R;

public class CreateCategoryFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    private ExerciseApi api;
    Button buttonCreate;
    Button buttonBack;
    EditText editTextName;
    EditText editTextDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_category, container, false);

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView

        return view;
    }


    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getApi();
        editTextName = view.findViewById(R.id.text_name);
        editTextDescription = view.findViewById(R.id.text_description);
        buttonBack = view.findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        buttonCreate = view.findViewById(R.id.btn_create);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCategory();
            }
        });
    }

    public void getApi(){
        MainActivity mainActivity = (MainActivity) getActivity();
        api = mainActivity.getApi();
    }

    public void createCategory(){
        //TODO: check if name and description are valid
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        //Log.d(name,description);
        api.createCategory(name,description);
        getActivity().onBackPressed();
    }
}