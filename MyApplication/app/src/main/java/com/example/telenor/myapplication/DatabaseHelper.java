package com.example.telenor.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by t905382 on 22-Dec-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbName = "test.db";
    private String tableUser = "Users";
    private String tableUser_ID = "userid";
    private String tableUser_Name = "username";
    private String tableUser_Password = "password";
    private String tableUser_Role = "role";

    private String tableItems = "Items";
    private String tableItems_ID = "itemid";
    private String tableItems_Name = "itemname";
    private String tableItems_Quantity = "itemquantity";


    public DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dropItemsTableQuery = "DROP TABLE IF EXISTS " + tableItems;
        sqLiteDatabase.execSQL(dropItemsTableQuery);

        String createUsersTableQuery = "Create Table "+tableUser+ "( "+ tableUser_ID+" Integer Primary Key AutoIncrement, "+tableUser_Name+" TEXT, "+tableUser_Password+" TEXT, "+tableUser_Role+" TEXT) ";
        sqLiteDatabase.execSQL(createUsersTableQuery);

        String createItemsTableQuery = "Create Table "+tableItems+ "( "+tableItems_ID+" Integer Primary Key AutoIncrement, "+tableItems_Name+" TEXT, "+tableItems_Quantity+" Integer)" ;
        sqLiteDatabase.execSQL(createItemsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableUser);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableItems);
        onCreate(sqLiteDatabase);

    }

    public boolean insertItem(String name, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tableItems_Name, name);
        contentValues.put(tableItems_Quantity, quantity);

        long result = db.insert(tableItems, null, contentValues);

        return result == -1 ? false : true;
    }

    public boolean insertUser(String userName, String password, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tableUser_Name, userName);
        contentValues.put(tableUser_Password, password);
        contentValues.put(tableUser_Role, role);

        long result = db.insert(tableUser, null, contentValues);

        return result == -1 ? false : true;
    }
    public void removeItem(String itemName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(tableItems_Name, itemName);

        db.execSQL("UPDATE " + tableItems + " SET " + tableItems_Quantity + "=" + tableItems_Quantity+ "-1" + " WHERE " + tableItems_ID+ "=? and ",
                new String[] { String.valueOf(itemName) } );

    }

}
