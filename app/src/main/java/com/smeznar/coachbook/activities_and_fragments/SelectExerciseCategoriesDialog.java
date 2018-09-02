package com.smeznar.coachbook.activities_and_fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.smeznar.coachbook.R;
import com.smeznar.coachbook.adapters.ExerciseCategoriesRecyclerAdapter;
import com.smeznar.coachbook.models.Category;

import java.util.ArrayList;
import java.util.List;

public class SelectExerciseCategoriesDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Button btnConfirm;
    private Button btnCancel;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Category> mCategoryList;
    private View.OnClickListener onClickListener;

    SelectExerciseCategoriesDialog(Activity activity, List<Category> mCategoryList) {
        super(activity);
        this.mCategoryList = mCategoryList;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_exercise_categories);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(onClickListener);
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);
        mRecyclerView = findViewById(R.id.exercise_category_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getOwnerActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ExerciseCategoriesRecyclerAdapter(mCategoryList);
        mRecyclerView.setAdapter(mAdapter);

    }

    public List<Category> getmCategoryList() {
        List<Category> token = new ArrayList<>();
        for (int i = 0; i < mCategoryList.size(); i++) {
            ExerciseCategoriesRecyclerAdapter.MyViewHolder view = (ExerciseCategoriesRecyclerAdapter.MyViewHolder) mRecyclerView.findViewHolderForAdapterPosition(i);
            if (view.isChecked()) {
                token.add(view.item);
            }
        }
        return token;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
