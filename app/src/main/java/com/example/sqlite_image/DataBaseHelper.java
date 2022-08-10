package com.example.sqlite_image;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper( Context context) {
        super(context, "imageDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table images(id integer Primary key, img blob not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists images");

    }
    public Boolean imageinsert(String x , Integer i){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {
            FileInputStream fileInputStream = new FileInputStream(x);
            byte[] imagebyte = new byte[fileInputStream.available()];
            fileInputStream.read(imagebyte);
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", i);
            contentValues.put("img", imagebyte);
            sqLiteDatabase.insert("images", null, contentValues);
            fileInputStream.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
