package com.huzaifa.vediocalling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LogOutActivity extends AppCompatActivity {

    private Button logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        logout = findViewById(R.id.logoutBtn);
        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();
            }

            private void signOutUser() {
                startActivity(new Intent(LogOutActivity.this,LoginActivity.class));
                finish();

            }

        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LogOutActivity.this,LoginActivity.class));
        finish();
    }
}