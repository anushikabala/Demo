package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button btnRegister;
    private EditText inputPassword,inputEmail,inputConfirmPassword,inputPhoneno;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputConfirmPassword=findViewById(R.id.inputConfirmPassword);
        inputPhoneno=findViewById(R.id.inputPhoneno);

        mAuth=FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Register.this);

        btnRegister=findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });




    }
    private void checkCredentials(){
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmPassword=inputConfirmPassword.getText().toString();
        String phoneno=inputPhoneno.getText().toString();

        if (email.isEmpty() || !email.contains("@"))
        {
            showError(inputEmail,"Your username is invalid!");
        }
        else if (password.isEmpty() || password.length()<7)
        {
            showError(inputPassword,"Your password is invalid!");
        }
        else if (confirmPassword.isEmpty() || !confirmPassword.equals(password))
        {
            showError(inputConfirmPassword,"Your password does not match!");
        }
        else if (phoneno.isEmpty() || phoneno.length()<10)
        {
            showError(inputPhoneno,"Invalid mobile Number");
        }
        else{
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait,white check your credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "Successfully Registered, You may LOGIN now", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(Register.this,MainActivity.class);
                        intent.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK));
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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