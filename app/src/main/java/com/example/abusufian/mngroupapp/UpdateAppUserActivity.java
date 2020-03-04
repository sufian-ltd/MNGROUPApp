package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAppUserActivity extends AppCompatActivity {

    EditText etFullName,etUserName,etPass;
    TextView tvUser;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_app_user);
        etFullName=findViewById(R.id.etFullName);
        etUserName=findViewById(R.id.etUserName);
        etPass=findViewById(R.id.etPass);
        tvUser=findViewById(R.id.tvUser);
        product= (Product) getIntent().getSerializableExtra("user");
        load();
    }

    public void save(View view) {
        String fn=etFullName.getText().toString();
        String un=etUserName.getText().toString();
        String pass=etPass.getText().toString();
        if(fn.equals("") || un.equals("") || pass.equals("")){
            Toast.makeText(this, "Please fill the boxes", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.updateAppUser(fn,un,pass,product.getId());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                    Toast.makeText(UpdateAppUserActivity.this, "App User is "+message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(UpdateAppUserActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void load(){
        etFullName.setText(product.getFullName());
        etUserName.setText(product.getUsername());
        etPass.setText(product.getPassword());
        tvUser.setText("Type : "+product.getUser());
    }
}
