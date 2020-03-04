package com.example.abusufian.mngroupapp;

import android.content.Intent;
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

public class ViewAppUserActivity extends AppCompatActivity {

    ListView listView;
    List<Product> list;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_app_user);
        listView=findViewById(R.id.listView);
        getUser();
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id=list.get(i).getId();
                String fullName=list.get(i).getFullName();
                String username=list.get(i).getUsername();
                String password=list.get(i).getPassword();
                String user=list.get(i).getUser();
                product=new Product(id,fullName,username,password,user);
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
        menu.add(1, v.getId(),1,"update");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getTitle()=="delete" && !product.getUser().equals("Admin")) {
            delete();
        }
        else if(item.getTitle()=="update"){
            Intent intent=new Intent(ViewAppUserActivity.this,UpdateAppUserActivity.class);
            intent.putExtra("user",product);
            startActivity(intent);
        }
        return true;
    }

    public void delete(){
        ApiInterface apiInterface=ApiClient.getRetrofit().create(ApiInterface.class);
        Call<ServerResponse> call=apiInterface.deleteUser(product.getId());
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse=response.body();
                String message=serverResponse.getResponse();
                if(message.equals("delete")){
                    Toast.makeText(ViewAppUserActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(ViewAppUserActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getUser(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<List<Product>> call = apiInterface.getAppUser();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                list = response.body();
                viewUser();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ViewAppUserActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void viewUser(){
        String str[]=new String[list.size()];
        for(int i=0;i<list.size();i++){
            str[i]=list.get(i).getFullName()+" : "+list.get(i).getUser();
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(ViewAppUserActivity.this,
                R.layout.list_item2,str);
        listView.setAdapter(adapter);
    }
}
