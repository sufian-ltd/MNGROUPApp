package com.example.abusufian.mngroupapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTotalCustomer extends AppCompatActivity {

    ListView listView;
    List<Product> list;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_total_customer);
        listView=findViewById(R.id.listView);
        getStock();
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                name=list.get(i).getName();
                return false;
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
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.deleteCustomer(name);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("delete")){
                    Toast.makeText(ViewTotalCustomer.this, "deleted", Toast.LENGTH_SHORT).show();
                    getStock();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ViewTotalCustomer.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getStock(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getTotalCustomer();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                viewStock();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ViewTotalCustomer.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewStock(){
        String str[]=new String[list.size()];
        for(int i=0;i<list.size();i++){
            str[i]=list.get(i).getName()+"  :  "+(list.get(i).getDebit()-list.get(i).getCredit());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(ViewTotalCustomer.this,
                R.layout.list_item2,str);
        listView.setAdapter(adapter);
    }
}
