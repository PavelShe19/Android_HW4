package com.example.android_ex4;

import android.database.Cursor;
import android.text.format.DateFormat;
import android.util.Log;

import java.util.Date;

public class Petek {
    public int id;
    public String title;
    public String content;
    public String status;
    public Date myDate;

    public Petek() {
    }

    public Petek(int i, String title, String content) {
        Log.i("new petek: ", "come on!");
        this.id = i;
        this.title = title;
        this.content = content;
        this.status = "Pending";
        Log.i("status: ", this.status.toString());
        this.myDate = new Date();
    }

    public static String TABLE_NAME = "Peteks";
    public static String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT, " +
                    "content TEXT, " +
                    "status TEXT, " +
                    "myDate DATETIME)";

    public Petek(Cursor c) {
        id = c.getInt(0);
        title = c.getString(1);
        content = c.getString(2);
        status = c.getString(3);
    }

    public String getSQLInsertString() {
        return "INSERT INTO " + TABLE_NAME +
                " (title, content, status, myDate) VALUES('" + title + "','" + content + "','" + status + "','" + myDate + "' )";
    }

    public static String SELECT_ALL =
            "SELECT * FROM " + TABLE_NAME;

    public String toString() {
        //Log.i("date: ", DateFormat.format(myDate));
        String response;
        if (this.status == null) {
            response = "ERROR";
        } else {
            response = this.status.toString();
        }
        Log.i("petekstring: ", response);
        return id + ", " + title + ", " + content + ". STATUS: " + response;
    }
}
