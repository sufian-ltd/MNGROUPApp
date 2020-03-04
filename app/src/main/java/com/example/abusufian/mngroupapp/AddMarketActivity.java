package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMarketActivity extends AppCompatActivity {

    EditText etName,etLoc,etContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_market);
        etName=findViewById(R.id.etName);
        etLoc=findViewById(R.id.etLoc);
        etContact=findViewById(R.id.etContact);
    }

    public void save(View view) {
        String name=etName.getText().toString();
        String loc=etLoc.getText().toString();
        String contact=etContact.getText().toString();
        if(name.equals("") || loc.equals("") || contact.equals("")){
            Toast.makeText(this, "Please Fill Boxes...!!!!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.addMarket(name,loc,contact,"-1",0,0,"");
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("inserted")){
                    Toast.makeText(AddMarketActivity.this, "This Market is Successfully saved", Toast.LENGTH_SHORT).show();
                    reload();
                }
                else if(message.equals("not inserted")){
                    Toast.makeText(AddMarketActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(AddMarketActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void reload(){
        etName.setText("");
        etContact.setText("");
        etLoc.setText("");
    }
}
