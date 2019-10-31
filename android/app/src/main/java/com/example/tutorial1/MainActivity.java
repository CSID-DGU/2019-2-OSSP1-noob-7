package com.example.tutorial1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    EditText et_id, et_pass;
    Button btn_login, btn_register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


        // 로그인 버튼을 클릭 시 수행


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // EditText에 현재 입력되어있는 값을 get 해온다.
                String userID = et_id.getText().toString();
                String userPass = et_pass.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success){ // 로그인에 성공한 경우
                                String userID = jsonObject.getString("userID");
                                String userPass = jsonObject.getString("userPassword");

                                Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.",Toast.LENGTH_SHORT).show();

                                // 아이디와 비밀번호를 받아서 메인으로 넘긴다.
                                Intent intent = new Intent(MainActivity.this, MainDisplayActivity.class);
                                intent.putExtra("userID",userID);
                                intent.putExtra("userPass",userPass);
                                startActivity(intent);

                            }
                            else { // 로그인에 실패한 경우
                                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                                return;

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                };

               LoginRequest loginRequest = new LoginRequest(userID, userPass, responseListener);
               RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
               queue.add(loginRequest);
            }
        });


        // 회원가입 버튼을 클릭 시 수행(회원가입 창 이동)


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

                startActivity(intent);

            }
        });



    }
}
