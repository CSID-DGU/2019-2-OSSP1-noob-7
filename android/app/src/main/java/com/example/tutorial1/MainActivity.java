package com.example.tutorial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText input_email, input_pw;
    Button login_button, signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_email = findViewById(R.id.input_email);
        input_pw = findViewById(R.id.input_pw);
        login_button = (Button)findViewById(R.id.login_button);

        signup_button = (Button)findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });
    }
}
