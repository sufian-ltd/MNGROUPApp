package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManipulateMarketActivity extends AppCompatActivity {

    EditText etDescription,etPaid,etDue;
    Spinner spinner;
    List<Product> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manipulate_market);
        etDescription=findViewById(R.id.etDes);
        etPaid=findViewById(R.id.etPaid);
        etDue=findViewById(R.id.etDue);
        spinner=findViewById(R.id.spinner);
        initialize();
    }

    public void save(View view){
        if(spinner.getSelectedItem()==null)
            return;
        String selected=spinner.getSelectedItem().toString();
        String description=etDescription.getText().toString();
        String p=etPaid.getText().toString();
        String d=etDue.getText().toString();
        if(selected.equals("Select Market") || selected.equals("")){
            Toast.makeText(this, "Please check market name.....", Toast.LENGTH_SHORT).show();
            return;
        }
        if(description.equals("") || p.equals("") || d.equals("")){
            Toast.makeText(this, "Please fill the all boxes.....", Toast.LENGTH_SHORT).show();
            return;
        }
        int paid=Integer.parseInt(p);
        int due=Integer.parseInt(d);
        String date=dateCalculation();
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.addMarket(selected,"","",description,paid,due,date);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("inserted")){
                    Toast.makeText(ManipulateMarketActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                    reload();
                }
                else if(message.equals("not inserted")){
                    Toast.makeText(ManipulateMarketActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ManipulateMarketActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void reload(){
        etPaid.setText("");
        etDue.setText("");
        etDescription.setText("");
        initialize();
    }

    public String dateCalculation() {
        Calendar cal;
        cal = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat();
        return sdf.format(cal.getTime());
    }

    public void initialize(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getMarketName();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ManipulateMarketActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showList(){
        String str[]=new String[list.size()+1];
        str[0]="Select Market";
        for(int i=0;i<list.size();i++){
            str[i+1]=list.get(i).getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.spinner_items,str);
        spinner.setAdapter(adapter);
    }
}
