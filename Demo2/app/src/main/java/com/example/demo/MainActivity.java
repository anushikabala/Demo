package com.example.demo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    EditText inputEmail,inputPassword;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
    private TextView register;
    ImageButton phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        register=findViewById(R.id.register);

        btnLogin=findViewById(R.id.btnLogin);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);

        phone=findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,PhoneAuth.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });

        register=findViewById(R.id.register);// Textview
        String text = "Don't have an account Register Here!";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        };
        ss.setSpan(clickableSpan,22,35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register.setText(ss);
        register.setMovementMethod(LinkMovementMethod.getInstance());

        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(MainActivity.this);



    }
    private void checkCredentials(){
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();


        if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Your email is invalid!");
        }
        else if (password.isEmpty() || password.length()<4)
        {
            showError(inputPassword,"Your password is invalid!");
        }

        else
        {
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait while check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(MainActivity.this, Options.class);
                        // intent.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                        startActivity(intent);


                    }
                    else{
                        mLoadingBar.dismiss();
                        Toast.makeText(MainActivity.this, "Incorrect email id / Password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}