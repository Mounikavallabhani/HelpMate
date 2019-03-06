package com.arkainfoteck.helpmate.Activitys;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DATABASE = "database.db";
    public static String TABLE ="mytable";
    public static String NAME ="name";
    public static String COMPANY ="company";
    public static String CITY ="city";
    public static String COUNTRY ="country";

    public static String TABLE_TIME_SLAT="timeslat";
    public static String TIME ="time";

    String br;
    String brdata;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        br = "CREATE TABLE "+TABLE+"("+NAME+ " Text, "+COMPANY+ " Text, "+CITY+ " Text, "+COUNTRY+ " Text);";
        brdata = "CREATE TABLE "+TABLE_TIME_SLAT+"("+TIME+ " Text);";
        db.execSQL(br);
        db.execSQL(brdata);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TIME_SLAT+" ;");
        onCreate(db);
    }


    public void insertdata(String name,String company ,String city,String country){

        System.out.print("Hello "+br);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(COMPANY, company);
        contentValues.put(CITY,city);
        contentValues.put(COUNTRY,country);
        db.insert(TABLE,null,contentValues);
    }


    public long inserttimedata(String name ){
        System.out.print("Hello123"+name);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(TIME, name);
        long count= db.insert(TABLE_TIME_SLAT,null,contentValues);
        System.out.print("H122123"+count);
        return count;
    }


    public ArrayList<DataModel> gettimedata(){

        ArrayList<DataModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_TIME_SLAT+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            dataModel.setTime_slat(name);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        for (DataModel mo:data ) {
            Log.i("Hellomo",""+mo.getTime_slat());
        }
        return data;
    }


    public ArrayList<DataModel> getdata(){

        ArrayList<DataModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            String country = cursor.getString(cursor.getColumnIndexOrThrow("country"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));

            dataModel.setName(name);
            dataModel.setApparent_house(city);
            dataModel.setLocality(country);
            dataModel.setNear_by_place(company);

            stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        for (DataModel mo:data ) {
            Log.i("Hellomo",""+mo.getName());
        }
        return data;
    }

    public int deleteConformOrderData(){
        SQLiteDatabase db=getWritableDatabase();
        int count= db.delete(TABLE_TIME_SLAT,null,null);
        db.close();
        return count;
    }

}