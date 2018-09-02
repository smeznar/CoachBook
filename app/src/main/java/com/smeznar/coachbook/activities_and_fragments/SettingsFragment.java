package com.smeznar.coachbook.activities_and_fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.smeznar.coachbook.R;

import io.realm.Realm;

public class SettingsFragment extends Fragment {

    private Button mUpdateDatabaseBtn;
    private ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mProgressBar = view.findViewById(R.id.progressBar);
        mUpdateDatabaseBtn = view.findViewById(R.id.btn_update_database);
        mUpdateDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mProgressBar.setVisibility(View.VISIBLE);
                mainActivity.getDatabaseHelper().populateDatabase(new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}