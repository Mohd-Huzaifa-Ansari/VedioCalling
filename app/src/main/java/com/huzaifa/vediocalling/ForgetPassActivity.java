package com.huzaifa.vediocalling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPassActivity extends AppCompatActivity {

    private Button forgetbtn;
    private EditText emailbox;
    private FirebaseAuth auth;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        auth =FirebaseAuth.getInstance();

        forgetbtn = findViewById(R.id.forgetbtn);
        emailbox = findViewById(R.id.emailBox);

        forgetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
            });
    }


            private void validateData() {

                email = emailbox.getText().toString();
                if(email.isEmpty()){
                    emailbox.setError("Required");
                }else{
                    forgetPass();
                }
            }

            private void forgetPass() {
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgetPassActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ForgetPassActivity.this,LoginActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(ForgetPassActivity.this, "Error"+ task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


    }
}