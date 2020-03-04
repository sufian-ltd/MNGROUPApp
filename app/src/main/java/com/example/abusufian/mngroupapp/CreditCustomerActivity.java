package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditCustomerActivity extends AppCompatActivity {

    EditText etDes,etQnt;
    TextView tvAvl;
    List<Product> list;
    List<Product> list2;
    Spinner spinner;
    int avl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_customer);
        etDes=findViewById(R.id.etDes);
        etQnt=findViewById(R.id.etQnt);
        tvAvl=findViewById(R.id.tvAvl);
        spinner=findViewById(R.id.spinner);
        initialize();
        getProduct();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name=spinner.getSelectedItem().toString();
                if(!name.equals("select customer")){
                avl=getAvailable(name);
                tvAvl.setText("Availability of "+name+" = "+avl);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void save(View view) {
        if(spinner.getSelectedItem()==null)
            return;
        String name=spinner.getSelectedItem().toString();
        String description=etDes.getText().toString();
        String quantity=etQnt.getText().toString();
        if(name.equals("select customer") || name.equals("")){
            Toast.makeText(this, "Please Select a customer", Toast.LENGTH_SHORT).show();
            return;
        }
        if(description.equals("") || quantity.equals("")){
            Toast.makeText(this, "Please fill all boxes", Toast.LENGTH_SHORT).show();
            return;
        }
        int credit=Integer.parseInt(quantity);
        if(avl<credit){
            Toast.makeText(this, "Sorry, there is shortage of amount", Toast.LENGTH_SHORT).show();
            return;
        }
        String date=dateCalculation();
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.addCustomer(name,description,0,credit,date);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                if(serverResponse.equals("inserted")){
                    Toast.makeText(CreditCustomerActivity.this, "Customer Successfully saved", Toast.LENGTH_SHORT).show();
                    reload();
                }
                else if(serverResponse.equals("not inserted")){
                    Toast.makeText(CreditCustomerActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(CreditCustomerActivity.this, "Please check internet connection",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void reload(){
        etDes.setText("");
        etQnt.setText("");
        tvAvl.setText("");
    }
    public int getAvailable(String name){
        int debit=0;
        int credit=0;
        for(int i=0;i<list.size();i++){
            if(list2.get(i).getName().equals(name)){
                debit=debit+list2.get(i).getDebit();
                credit=credit+list2.get(i).getCredit();
            }
        }
        return debit-credit;
    }
    public void initialize(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getCustomerName();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(CreditCustomerActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String dateCalculation() {
        Calendar cal;
        cal = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat();
        return sdf.format(cal.getTime());
    }
    public void showList(){
        String str[]=new String[list.size()+1];
        str[0]="select customer";
        for(int i=0;i<list.size();i++){
            str[i+1]=list.get(i).getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.spinner_items,str);
        spinner.setAdapter(adapter);
    }
    public void getProduct(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getCustomers();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list2 = response.body();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(CreditCustomerActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
