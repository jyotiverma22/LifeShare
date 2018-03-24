package com.example.hp.lifeshare.BloodBankDetails;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandlerBankHistory extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;


    private static final String DATABASE_NAME = "LifeShare";

    private static final String TABLE_BANK_HISTORY = "bank_history";

    private static final String KEY_ID = "patient_id";
    private static final String KEY_GROUP = "blood_group";
    private static final String KEY_COUNT = "units";
    private static final String KEY_TIME = "time";

    DatabaseHandlerBankHistory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_BANK_HISTORY + "("
                + KEY_ID + " INTEGER," + KEY_GROUP + " TEXT,"
                + KEY_COUNT + " INTEGER, "+KEY_TIME + " TEXT " + ")";
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANK_HISTORY);
        onCreate(db);
    }

   void additem(BloodBankHistoryItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, item.getPatient_id());
        values.put(KEY_GROUP, item.getGroup());
        values.put(KEY_COUNT,item.getCount());
        values.put(KEY_TIME,item.getTime());

        db.insert(TABLE_BANK_HISTORY, null, values);

        db.close();
    }

    public List<BloodBankHistoryItem> getAllHistory() {

        List<BloodBankHistoryItem> itemList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_BANK_HISTORY;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BloodBankHistoryItem item = new BloodBankHistoryItem(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(2)),cursor.getString(3));
              itemList.add(item);
            } while (cursor.moveToNext());
        }


        return itemList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BANK_HISTORY,  " 0 = 0", new String[]{});
        db.close();
    }



}