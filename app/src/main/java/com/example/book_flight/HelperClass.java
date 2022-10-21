package com.example.book_flight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperClass extends SQLiteOpenHelper {

    public HelperClass(@Nullable Context context) {
        super(context, "ODS.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry="create table employeedetails(id integer primary key autoincrement,froms text,too text)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

//        String qry="DROP TABLE IF EXISTS ";
//        db.execSQL(qry);
//        onCreate(db);
    }
    public String addrecord(String fromStr, String toStr){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("froms",fromStr);
        cv.put("too",toStr);
        float res=db.insert("employeedetails",null,cv);

        if (res==-1)
            return "Data Insertion Failed";
        else
            return "Successfully inserted Data";
    }
    public Cursor readallData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String qry="select * from employeedetails order by id desc";
        Cursor cursor=sqLiteDatabase.rawQuery(qry,null);
        return cursor;
    }
}
