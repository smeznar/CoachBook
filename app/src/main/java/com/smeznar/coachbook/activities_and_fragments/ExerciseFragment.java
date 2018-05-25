package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smeznar.coachbook.adapters.CategoriesRecyclerAdapter;
import com.smeznar.coachbook.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.models.Category;

import java.util.List;

public class ExerciseFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ExerciseApi api;
    private Button createCategoryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        return view;
    }

    List<Category> getNames(){
        try{
            List<Category> names;
            names = api.getCategories();
            return names;
        } catch (Exception e){
            Log.e("ERROR ExerciseFragment",e.getMessage());
        }
        return null;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getApi();
        createCategoryBtn = view.findViewById(R.id.buttonCreateCategory);
        createCategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                CreateCategoryFragment fragmentCreateCategory = new CreateCategoryFragment();
                fragmentTransaction.replace(R.id.flContent, fragmentCreateCategory);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mRecyclerView = view.findViewById(R.id.categories_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CategoriesRecyclerAdapter(getNames());
        mRecyclerView.setAdapter(mAdapter);
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    private void getApi(){
        try {
            MainActivity mainActivity = (MainActivity) getActivity();
            api = mainActivity.getApi();
        } catch (Exception e){
            Log.e("ERROR ExerciseFragment",e.getMessage());
        }
    }
}