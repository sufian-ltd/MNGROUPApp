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

public class AddAppUser extends AppCompatActivity {

    EditText etName,etUserName,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_app_user);
        etName=findViewById(R.id.etFullName);
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPass);
    }

    public void save(View view) {
        String name=etName.getText().toString();
        String username=etUserName.getText().toString();
        String password=etPassword.getText().toString();
        if(name.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(this, "Please fill the boxes", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.addAppUser(name,username,password,"User");
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("exist")){
                    Toast.makeText(AddAppUser.this, "Already exist...Please try again", Toast.LENGTH_SHORT).show();

                }
                else if(message.equals("inserted")){
                    Toast.makeText(AddAppUser.this, "App User is Successfully added", Toast.LENGTH_SHORT).show();
                    reload();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(AddAppUser.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void reload(){
        etName.setText("");
        etUserName.setText("");
        etPassword.setText("");
    }
}
