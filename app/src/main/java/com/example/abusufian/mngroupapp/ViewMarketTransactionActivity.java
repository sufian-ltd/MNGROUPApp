package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMarketTransactionActivity extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    List<Product> list;
    List<Product> list2;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_market_transaction);
        listView=findViewById(R.id.listView);
        spinner=findViewById(R.id.spinner);
        initialize();
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                id=list2.get(i).getId();
                return false;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                search();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Action");
        menu.add(0, v.getId(),0,"delete");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getTitle()=="delete")
        {
            delete();
        }
        return true;
    }

    public void delete(){
        if(id==-1)
            return;
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.deleteMarketTransaction(id);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("delete")){
                    Toast.makeText(ViewMarketTransactionActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ViewMarketTransactionActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void search() {
        if(spinner.getSelectedItem()==null)
            return;
        String name=spinner.getSelectedItem().toString();
        if(name.equals("select market") || name.equals(""))
            return;
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getMarketTransaction(name);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list2 = response.body();
                setAdapter(list2);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ViewMarketTransactionActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setAdapter(List<Product> list2){
        int paid=0,due=0;
        for(int i=0;i<list2.size();i++){
            paid+=list2.get(i).getPaid();
            due+=list2.get(i).getDue();
        }
        Product product=new Product(-1,"","Total",paid,due);
        list2.add(product);
        MarketAdapter adapter=new MarketAdapter(this,list2);
        listView.setAdapter(adapter);
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
                Toast.makeText(ViewMarketTransactionActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showList(){
        if(list.isEmpty())
            return;
        String str[]=new String[list.size()+1];
        str[0]="select market";
        for(int i=0;i<list.size();i++){
            str[i+1]=list.get(i).getName();
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
                R.layout.spinner_items,str);
        spinner.setAdapter(adapter);
    }
}
