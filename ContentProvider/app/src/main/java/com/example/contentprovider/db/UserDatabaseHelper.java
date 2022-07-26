package com.example.contentprovider.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

import com.example.contentprovider.Utils.Constants;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "UserDatabaseHelper";

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate....");

//        创建数据库
        String createSql = "create table "
                + Constants.TABLE_NAME +
                " (" + Constants.FIELD_ID +
                " integer primary key autoincrement, " + Constants.FIELD_USER_NAME +
                " varchar(30), " + Constants.FIELD_PASSWORD +
                " varchar(32),"+Constants.FIELD_SEX+
                " varchar(5) ," + Constants.FIELD_AGE +
                " integer)";
        db.execSQL(createSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
