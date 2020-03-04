package com.example.abusufian.mngroupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etUserName,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPass);
    }

    public void login(View view) {
        String username=etUserName.getText().toString();
        String password=etPassword.getText().toString();
        if(username.equals("") || password.equals("")){
            Toast.makeText(this, "Please fill the boxes", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.login(username,password);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("Admin")){
                    Toast.makeText(LoginActivity.this, "Admin Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("user","admin");
                    startActivity(intent);
                }
                else if(message.equals("User")){
                    Toast.makeText(LoginActivity.this, "User Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("user","user");
                    startActivity(intent);
                }
                else if(message.equals("not exist")){
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void credit(View view) {
        Intent intent=new Intent(LoginActivity.this,DeveloperActivity.class);
        startActivity(intent);
    }
}
