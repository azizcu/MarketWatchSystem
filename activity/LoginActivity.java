package com.example.marketwatchsystem.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marketwatchsystem.Class.DBHelper;
import com.example.marketwatchsystem.Model.UserType;
import com.example.marketwatchsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog loadingBar;
    private Button LoginButton, PhoneLoginButton;
    private EditText UserEmail, UserPassword;
    private TextView NeedNewAccountLink;

    FirebaseFirestore db;
    DocumentReference ref;
    private CheckBox checkBox;
    private String type="NonAdmin";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        db = FirebaseFirestore.getInstance();

        InitializeFields();


        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SendUserToRegisterActivity();
            }
        });


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AllowUserToLogin();
            }
        });

        PhoneLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent phoneLoginIntent = new Intent(LoginActivity.this, PhoneLoginActivity.class);
                phoneLoginIntent.putExtra("type",type);
                startActivity(phoneLoginIntent);
            }
        });
    }




    @Override
    protected void onStart()
    {
        super.onStart();

       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // if (user != null)
        {
        //    SendUserToMainActivity();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void AllowUserToLogin()
    {
        final String email = UserEmail.getText().toString();
        final String password = UserPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Sign In");
            loadingBar.setMessage("Please wait....");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(LoginActivity.this, "Logged in Successful" , Toast.LENGTH_SHORT).show();
                                SendUserToMainActivity();

                            }
                            else
                            {
                                //String message = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Your Information is not Valid", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });

        }
    }



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            type="Admin";
        }

    }

    private void InitializeFields()
    {
        LoginButton = (Button) findViewById(R.id.login_button);
        PhoneLoginButton = (Button) findViewById(R.id.phone_login_button);
        UserEmail = (EditText) findViewById(R.id.login_email);
        UserPassword = (EditText) findViewById(R.id.login_password);
        NeedNewAccountLink = (TextView) findViewById(R.id.need_new_account_link);
        checkBox = findViewById(R.id.checkbox);
        loadingBar = new ProgressDialog(this);
    }



    private void SendUserToMainActivity()
    {
        UserType.getUserTypeInstance().setType(type);
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    private void SendUserToRegisterActivity()
    {
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(registerIntent);
    }


}

