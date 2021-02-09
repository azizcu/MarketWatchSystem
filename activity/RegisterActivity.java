package com.example.marketwatchsystem.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketwatchsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail, userPassword,userName;
    private Button createAccountButton;
    private TextView alreadyHaveAccountLink;
    private FirebaseAuth mAuth;
    private DatabaseReference rootRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        InitializeFields();

         alreadyHaveAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToLoginActivity();
            }
        });
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount() {
        final String name = userName.getText().toString();
        final String email = userEmail.getText().toString();
        final String password = userPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please enter your email..", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Please enter your password..", Toast.LENGTH_SHORT).show();
        }
        else{
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Plaese wait, while we are creating new account for you...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                rootRef.child("Users").child(currentUserId).setValue("");

                                Toast.makeText(RegisterActivity.this, "Account created Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                CreateNewDatabase();
                            }
                            else{
                                String message = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Error: "+message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });


        }

    }

    private void CreateNewDatabase() {
        final String name = userName.getText().toString();
        final String email = userEmail.getText().toString();
        final String password = userPassword.getText().toString();

        HashMap<String, Object> TimeAndDate = new HashMap<>();
        TimeAndDate.put("DateAndTime","");
        String currentUserId = mAuth.getCurrentUser().getUid();

        DocumentReference time = FirebaseFirestore.getInstance().collection("Client").document(currentUserId);
        time.set(TimeAndDate);

        Map<String, Object> reg_entry = new HashMap<>();
        reg_entry.put("Id", mAuth.getUid());
        reg_entry.put("Name", name);
        reg_entry.put("Email", email);
        reg_entry.put("Password", password);
       // reg_entry.put("Online_status", "offline");


        FirebaseFirestore.getInstance().collection("Client").document(currentUserId).set(reg_entry)
                .addOnSuccessListener(new OnSuccessListener < Void > () {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterActivity.this, "User Registered",
                                Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "ERROR" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.toString());
                    }
                });

    }


    private void InitializeFields() {
        userName = findViewById(R.id.register_name);
        userEmail = findViewById(R.id.register_email);
        userPassword = findViewById(R.id.register_password);
        createAccountButton = findViewById(R.id.register_button);
        alreadyHaveAccountLink = findViewById(R.id.already_account_link);
        loadingBar = new ProgressDialog(this);
    }

    private void SendUserToLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


}

