package com.afebrii.vsga2024;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);

    Log.d("table", CREATE_TABLE_STUDENTS);

    public static String DATABASE_NAME = "student_database";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_STUDENT = "students";
    public static final String KEY_ID = "id";
    public static final String KEY_FIRSTNAME = "name";

    public static final String CREATE_TABLE_STUDENTS = "CREATE TABLE " + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENT + "'");
    }

    public void addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
        long insert = db.insert(TABLE_STUDENT), null, values;

        return insert;
    }


    public ArrayList<String> getAllStudentsList() {
        ArrayList<String> studentArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                studentArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentArrayList.toString());
        }
        return studentArrayList
    }
}
