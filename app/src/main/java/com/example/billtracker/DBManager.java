package com.example.billtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    //database name
    private static final String DB_NAME = "BillsDB";

    //database version
    static final int dbVersion = 1;

    //table name
    public static final String TABLE_NAME = "BillsTable";

    //table columns
    public static final String _ID = "id";
    public static final String BILL_NAME = "billName";
    public static final String DUE_DATE = "dueDate";

    //constructor
    public DBManager(Context context){
        super(context, DB_NAME, null, dbVersion);
    }

    //creating table query
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME + " (" + _ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + BILL_NAME + " TEXT,"
                + DUE_DATE + " TEXT)";
        db.execSQL(query);
    }

    public void addNewBill(String billName, String dueDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(BILL_NAME, billName);
        contentValue.put(DUE_DATE, dueDate);
        db.insert(TABLE_NAME, null, contentValue);
        db.close();
    }

    public void deleteBill(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{name});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //public void close() {dbHelper.close();}

    public Cursor fetch() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[] { _ID, BILL_NAME, DUE_DATE };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //update a single entry
    public int update(long _id, String billName, String dueDate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BILL_NAME, billName);
        contentValues.put(DUE_DATE, dueDate);
        int i = db.update(TABLE_NAME, contentValues, _ID
                + " = " + _id, null);
        return i;
    }

    //delete an entry
    public void delete(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, _ID + "=" + _id, null);
        db.close();
    }

    public ArrayList<Bills> readBills(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorBills = db.query(TABLE_NAME, null, null, null, null, null, DUE_DATE+" ASC", null);
        ArrayList<Bills> billsArrayList = new ArrayList<>();

        if(cursorBills.moveToFirst()){
            do{
                billsArrayList.add(new Bills(
                        cursorBills.getString(1),
                        cursorBills.getString(2)));
            }while(cursorBills.moveToNext());
        }
        cursorBills.close();
        return billsArrayList;
    }
}
