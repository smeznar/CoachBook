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

import com.smeznar.coachbook.API.ExerciseApi;
import com.smeznar.coachbook.R;
import com.smeznar.coachbook.adapters.CategoriesRecyclerAdapter;
import com.smeznar.coachbook.interfaces.ICustomItemClickListener;
import com.smeznar.coachbook.models.Category;

import java.util.List;

public class SelectCategoryFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ExerciseApi mApi;
    private Button mCreateCategoryButton;
    private Button mCreateExerciseButton;
    private List<Category> mCategoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select_category, container, false);
    }

    List<Category> getNames(){
        try{
            List<Category> names;
            names = mApi.getCategories();
            return names;
        } catch (Exception e){
            Log.e("ERROR SelectCategory",e.getMessage());
        }
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getApi();
        mCreateCategoryButton = view.findViewById(R.id.btn_create_category);
        mCreateCategoryButton.setOnClickListener(new View.OnClickListener() {
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

        mCreateExerciseButton = view.findViewById(R.id.btn_create_exercise);
        mCreateExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                CreateExerciseFragment fragmentCreateExercise = new CreateExerciseFragment();
                fragmentTransaction.replace(R.id.flContent, fragmentCreateExercise);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mRecyclerView = view.findViewById(R.id.categories_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mCategoryList = getNames();
        mAdapter = new CategoriesRecyclerAdapter(mCategoryList, new ICustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("onClick RecyclerView", "clicked position:" + position);
                Log.d("onClick RecyclerView",mCategoryList.get(position).getmCategoryDescription());
            }
        });
        mRecyclerView.setAdapter(mAdapter);

    }

    private void getApi(){
        try {
            MainActivity mainActivity = (MainActivity) getActivity();
            mApi = mainActivity.getApi();
        } catch (Exception e){
            Log.e("ERROR SelectCategory",e.getMessage());
        }
    }
}