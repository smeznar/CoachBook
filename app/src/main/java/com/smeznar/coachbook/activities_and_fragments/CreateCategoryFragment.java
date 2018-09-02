package com.smeznar.coachbook.activities_and_fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.smeznar.coachbook.API.ExerciseApi;
import com.smeznar.coachbook.R;

public class CreateCategoryFragment extends Fragment {

    private ExerciseApi api;
    Button buttonCreate;
    Button buttonBack;
    EditText editTextName;
    EditText editTextDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_category, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getApi();
        editTextName = view.findViewById(R.id.text_name);
        editTextDescription = view.findViewById(R.id.text_description);
        editTextDescription.setImeOptions(EditorInfo.IME_ACTION_DONE);
        editTextDescription.setRawInputType(InputType.TYPE_CLASS_TEXT);
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
        if(name.equals("") || description.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("both category name and it's description are required")
                    .setTitle("ENTER ALL DATA");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            api.createCategory(name, description);
            getActivity().onBackPressed();
        }
    }
}