package com.smeznar.coachbook.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smeznar.coachbook.R;
import com.smeznar.coachbook.interfaces.ICustomItemClickListener;
import com.smeznar.coachbook.models.Category;

import java.util.List;

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.MyViewHolder> {

    private List<Category> mCategoryList;
    private ICustomItemClickListener mListener;

    public CategoriesRecyclerAdapter(List<Category> categoriesList, ICustomItemClickListener listener) {
        this.mCategoryList = categoriesList;
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryName;
        public Category item;

        public MyViewHolder(View view) {
            super(view);
            categoryName = view.findViewById(R.id.category_text_view);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Category category = mCategoryList.get(position);
        holder.categoryName.setText(category.getmCategoryName());
        holder.item = category;
        if(!holder.itemView.hasOnClickListeners()){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mListener.onItemClick(v, holder.getAdapterPosition());
                }
        });
        }
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }
}
