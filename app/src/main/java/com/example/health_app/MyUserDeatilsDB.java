package com.example.health_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyUserDeatilsDB extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="UserDetails.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="my_users";
    private static final String  COLUMN_ID="_id";
    private static final String COLUMN_NAME="Name";
    private static final String COLUMN_AGE="Age";
    private static final String COLUMN_DOB="Dob";
    private static final String COLUMN_GENDER="Gender";
    private static final String COLUMN_ADDRESS="Address";
    private static final String COLUMN_CONTACT="Contact";
    private static final String COLUMN_DR_NAME="DoctorName";
    private static final String COLUMN_HEIGHT="Height";
    private static final String COLUMN_WEIGHT="Weight";
    private static final String COLUMN_OXYGEN="Oxygen";
    private static final String COLUMN_BP="Bp";


    public MyUserDeatilsDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_AGE + " INTEGER, " +
                        COLUMN_DOB + " TEXT, " +
                        COLUMN_GENDER + " TEXT, " +
                        COLUMN_ADDRESS + " TEXT, " +
                        COLUMN_CONTACT + " TEXT, "+
                        COLUMN_DR_NAME + " TEXT, " +
                        COLUMN_HEIGHT + " TEXT, " +
                        COLUMN_WEIGHT + " TEXT, " +
                        COLUMN_OXYGEN + " TEXT, " +
                        COLUMN_BP + " TEXT ); " ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDetails(String name,int age,String dob,String gender,String address,String contact,String dr_name,String height,String weight,String oxygen,String bp){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_AGE,age);
        cv.put(COLUMN_DOB,dob);
        cv.put(COLUMN_GENDER,gender);
        cv.put(COLUMN_ADDRESS,address);
        cv.put(COLUMN_CONTACT,contact);
        cv.put(COLUMN_DR_NAME,dr_name);
        cv.put(COLUMN_HEIGHT,height);
        cv.put(COLUMN_WEIGHT,weight);
        cv.put(COLUMN_OXYGEN,oxygen);
        cv.put(COLUMN_BP,bp);
        long result= db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
              Toast.makeText(context,"Success!",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
