package com.example.abusufian.mngroupapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AddProductActivity extends AppCompatActivity {

    EditText etName,etDescription,etQuantity;
    Spinner spinner;
    List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        etName=findViewById(R.id.etName);
        etDescription=findViewById(R.id.etDes);
        etQuantity=findViewById(R.id.etQnt);
        spinner=findViewById(R.id.spinner);
        initialize();
    }

    public void save(View view){
        if(spinner.getSelectedItem()==null)
            return;
        String selected=spinner.getSelectedItem().toString();
        String name=etName.getText().toString();
        String description=etDescription.getText().toString();
        String d=etQuantity.getText().toString();
        String temp="";
        if(!selected.equals("select product") && !name.equals("")){
            Toast.makeText(this, "Please check product name.....", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected.equals("select product") && name.equals("") ){
            Toast.makeText(this, "Please enter product name.....", Toast.LENGTH_SHORT).show();
            return;
        }
        if(selected.equals("select product") && !name.equals("")){
            temp=name;
        }
        else if(!selected.equals("select product") && name.equals("")){
            temp=selected;
        }
        if(description.equals("") || d.equals("")){
            Toast.makeText(this, "Please enter enter description and quantity.....", Toast.LENGTH_SHORT).show();
            return;
        }
        int debit=Integer.parseInt(d);
        String date=dateCalculation();
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.addProduct(temp,description,debit,0,date);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("inserted")){
                    Toast.makeText(AddProductActivity.this, "Product Successfully saved", Toast.LENGTH_SHORT).show();
                    reload();
                }
                else if(message.equals("not inserted")){
                    Toast.makeText(AddProductActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void reload(){
        etName.setText("");
        etQuantity.setText("");
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
        Call<List<Product>> call = apiInterface.getProductName();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(AddProductActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showList(){
        String str[]=new String[list.size()+1];
        str[0]="select product";
        for(int i=0;i<list.size();i++){
            str[i+1]=list.get(i).getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.spinner_items,str);
        spinner.setAdapter(adapter);
    }


}
