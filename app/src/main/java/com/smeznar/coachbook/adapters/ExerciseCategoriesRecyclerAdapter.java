package com.smeznar.coachbook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.smeznar.coachbook.R;
import com.smeznar.coachbook.models.Category;

import java.util.List;

public class ExerciseCategoriesRecyclerAdapter extends RecyclerView.Adapter<ExerciseCategoriesRecyclerAdapter.MyViewHolder> {

    private List<Category> mCategoryList;

    public ExerciseCategoriesRecyclerAdapter(List<Category> categoriesList) {
        this.mCategoryList = categoriesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private CheckBox checkBox;
        public Category item;

        public MyViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.category_text_view);
            checkBox = view.findViewById(R.id.checkBox);
        }

        public boolean isChecked(){
            return checkBox.isChecked();
        }
    }

    @Override
    public ExerciseCategoriesRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_category_list_row, parent, false);
        return new ExerciseCategoriesRecyclerAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ExerciseCategoriesRecyclerAdapter.MyViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);
        holder.categoryName.setText(category.getmCategoryName());
        holder.item = category;
        holder.checkBox.setChecked(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.checkBox.setChecked(!holder.checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
