package com.example.tutorial1;


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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText emailID, password;
    Button login_button, signup_button;

    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login_button = (Button)findViewById(R.id.login_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainDisplayActivity.class);

                startActivity(intent);
            }
        });

        signup_button = (Button)findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailID.getText().toString();    // 이메일 받은 걸 저장
                String pwd = password.getText().toString();     // 비밀번호 받은 걸 저장

                // 이메일 또는 비밀번호 입력 안할 시 에러 알림
                if(email.isEmpty()){
                    emailID.setError("Please enter your e-mail");
                    emailID.requestFocus();

                }
                else if(pwd.isEmpty()){
                    password.setError("Please enter your password");

                }
                // 이메일과 비밀번호 둘 다 비어있을 경우:
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are empty!",Toast.LENGTH_SHORT).show();

                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){


                            }

                        }
                    });

                }

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });


        mFirebaseAuth = FirebaseAuth.getInstance();

        emailID = findViewById(R.id.input_email);
        password = findViewById(R.id.input_pw);

    }
}
