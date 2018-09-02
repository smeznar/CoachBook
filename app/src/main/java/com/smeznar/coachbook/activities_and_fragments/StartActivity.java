package com.smeznar.coachbook.activities_and_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smeznar.coachbook.R;

public class StartActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mAuth = FirebaseAuth.getInstance();

        //TODO: Edit Screen to look better
        mEmailEditText = findViewById(R.id.email_editText);
        mPasswordEditText = findViewById(R.id.password_editText);
        mBtnLogin = findViewById(R.id.btn_login);
        mProgressBar = findViewById(R.id.progressBar);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });

        //TODO: Registration Screen
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            //TODO: Do Something with user
            goToMainActivity();
        }
    }

    void signInUser(){
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if(!email.isEmpty() && !password.isEmpty()){
            mProgressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mProgressBar.setVisibility(View.GONE);
                                Log.d("StartActivity", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //TODO: Do Something with user
                                goToMainActivity();
                            } else {
                                mProgressBar.setVisibility(View.GONE);
                                Toast.makeText(getBaseContext(),"Authentication failed",Toast.LENGTH_SHORT).show();
                                Log.w("StartActivity", "signInWithEmail:failure", task.getException());
                            }
                        }
                    });
        }
    }

    void goToMainActivity(){
        startActivity(new Intent(this,MainActivity.class));
    }
}
