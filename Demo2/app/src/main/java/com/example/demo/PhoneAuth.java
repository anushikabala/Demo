package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity {
    EditText inputph,inputotp;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    String verify_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        inputph=(EditText)findViewById(R.id.inputph);
        inputotp=(EditText)findViewById(R.id.inputotp);
        auth = FirebaseAuth.getInstance();

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
            @Override
            public void onCodeSent(String s,PhoneAuthProvider.ForceResendingToken forceResendingToken){
                super.onCodeSent(s, forceResendingToken);
                verify_code =s;
                Toast.makeText(getApplicationContext(),"Code Sent to the respective number",Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void send_sms(View v){
        String number=inputph.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,60, TimeUnit.SECONDS,this,mCallback
        );
    }

    public void signInWithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User Signed in successfully",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    public void verify(View v){
        String otp=inputotp.getText().toString();
            verifyPhoneNumber(verify_code,otp);
            Intent intent = new Intent(PhoneAuth.this,Options.class);
            startActivity(intent);

    }
    public void verifyPhoneNumber(String verification,String otp){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verification,otp);
        signInWithPhone(credential);


    }
}