package com.example.supplementary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.example.supplementary.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "menu.db";

    public DBHelper( Context context) {super(context, DATABASE_NAME, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + MenuItems.Items.TABLE_NAME + " (" +
                        MenuItems.Items._ID+ " INTEGER PRIMARY KEY," +
                        MenuItems.Items.COLUMN_NAME_ITEM+ " TEXT,"+
                        MenuItems.Items.COLUMN_NAME_PRICE+ " TEXT)";
                        MenuItems.Items.COLUMN_NAME_DESCRIPTION+ " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public Long addInfo(String userName, String password){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MenuItems.Items.COLUMN_NAME_ITEM, item);
        values.put(MenuItems.Items.COLUMN_NAME_PRICE, price);
        values.put(MenuItems.Items.COLUMN_NAME_DESCRIPTION, description);

        return db.insert(MenuItems.Items.TABLE_NAME, null, values);
    }

    // new code
    public List readAllInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String [] projection = {
                MenuItems.Items._ID,
                MenuItems.Items.COLUMN_NAME_ITEM,
                MenuItems.Items.COLUMN_NAME_PRICE,
                MenuItems.Items.COLUMN_NAME_DESCRIPTION
        };

        String sortOrder = MenuItems.Items.COLUMN_NAME_ITEM + " DESC";

        Cursor cursor = db.query(
                MenuItems.Items.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List info = new ArrayList<>();

        while (cursor.moveToNext()){
            String item = cursor.getString(cursor.getColumnIndexOrThrow(MenuItems.Items.COLUMN_NAME_ITEM));
            String price = cursor.getString(cursor.getColumnIndexOrThrow(MenuItems.Items.COLUMN_NAME_PRICE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MenuItems.Items.COLUMN_NAME_DESCRIPTION));

            info.add(item+": "+price+": "+description);
        }
        cursor.close();

        return info;
    }

    public void deleteInfo(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String selection MenuItems.Items.COLUMN_NAME_ITEM + " LIKE ?";
        String [] selectionArgs = { userName };
        db.delete(MenuItems.Items.TABLE_NAME,selection,selectionArgs);


    }

    public void updateInfo(View view, String item, String price, String description){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(MenuItems.Items.COLUMN_NAME_PRICE, price);

        String selection = MenuItems.Items.COLUMN_NAME_ITEM + " Like ?";
        String[] selectionArgs = {item};

        int count = db.update(
                MenuItems.Items.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        Snackbar snackbar = Snackbar.make(view, count + " rows effected",Snackbar.LENGTH_LONG);
        snackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
        snackbar.show();



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
