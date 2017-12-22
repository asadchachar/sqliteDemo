package com.example.telenor.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        databaseHelper.insertItem("Eggs", 10);
        databaseHelper.insertItem("Bread", 20);
        databaseHelper.insertItem("Rice", 25);
        databaseHelper.insertItem("Beans", 100);
        databaseHelper.insertItem("Salt", 50);
        databaseHelper.insertItem("Sauce", 110);

        databaseHelper.insertUser("ali", "admin", "ADMIN");
        databaseHelper.insertUser("testAdmin", "admin", "ADMIN");
        databaseHelper.insertUser("ahmad", "user", "ADMIN");
        databaseHelper.insertUser("testuser", "user", "USER");

      //  databaseHelper.removeItem("10");


    }
}
