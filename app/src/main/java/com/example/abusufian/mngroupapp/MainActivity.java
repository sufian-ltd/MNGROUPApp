package com.example.abusufian.mngroupapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String user;
    LinearLayout l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=getIntent().getStringExtra("user");
    }

    public void viewProduct(View view) {
        Intent intent=new Intent(MainActivity.this,ViewProductActivity.class);
        startActivity(intent);
    }

    public void addProduct(View view) {
        Intent intent=new Intent(MainActivity.this,AddProductActivity.class);
        startActivity(intent);
    }

    public void useProduct(View view) {
        Intent intent=new Intent(MainActivity.this,UseProductActivity.class);
        startActivity(intent);
    }

    public void viewStock(View view) {
        Intent intent=new Intent(MainActivity.this,ViewStockActivity.class);
        startActivity(intent);
    }

    public void debitCustomer(View view) {
        Intent intent=new Intent(MainActivity.this,DebitCustomerActivity.class);
        startActivity(intent);
    }

    public void creditCustomer(View view) {
        Intent intent=new Intent(MainActivity.this,CreditCustomerActivity.class);
        startActivity(intent);
    }

    public void viewTransaction(View view) {
        Intent intent=new Intent(MainActivity.this,ViewTransactionActivity.class);
        startActivity(intent);
    }

    public void viewTotalCustomer(View view) {
        Intent intent=new Intent(MainActivity.this,ViewTotalCustomer.class);
        startActivity(intent);
    }

    public void addMarket(View view) {
        Intent intent=new Intent(MainActivity.this,AddMarketActivity.class);
        startActivity(intent);
    }

    public void viewMarketTransaction(View view) {
        Intent intent=new Intent(MainActivity.this,ViewMarketTransactionActivity.class);
        startActivity(intent);
    }

    public void viewMarketReport(View view) {
        Intent intent=new Intent(MainActivity.this,ViewTotalMarketActivity.class);
        startActivity(intent);
    }

    public void manipulateMarket(View view) {
        Intent intent=new Intent(MainActivity.this,ManipulateMarketActivity.class);
        startActivity(intent);
    }

    public void addAppUser(View view) {
        if(user.equals("admin")){
            Intent intent=new Intent(MainActivity.this,AddAppUser.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Sorry...!!!It's only for Admin access...!!!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewAppUser(View view) {

        if(user.equals("admin")){
            Intent intent=new Intent(MainActivity.this,ViewAppUserActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Sorry...!!!It's only for Admin access...!!!!!", Toast.LENGTH_SHORT).show();
        }
    }


}
