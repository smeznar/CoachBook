package com.smeznar.coachbook;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smeznar.coachbook.models.Category;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.MyViewHolder> {

    private List<Category> categoryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView category;
//        public LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
//            linearLayout = (LinearLayout) view.findViewById(R.id.category_linear_layout);
            category = (TextView) view.findViewById(R.id.category_text_view);
        }
    }


    public CategoriesRecyclerAdapter(List<Category> categoriesList) {
        this.categoryList = categoriesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.category.setText(category.getmCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
