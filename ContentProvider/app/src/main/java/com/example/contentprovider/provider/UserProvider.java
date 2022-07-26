package com.example.contentprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.contentprovider.Utils.Constants;
import com.example.contentprovider.db.UserDatabaseHelper;

public class UserProvider extends ContentProvider {

    private static final String TAG = "UserProvider";
    private UserDatabaseHelper userDatabaseHelper = null;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int USER_MATCH_CODE = 1;

    static {
        uriMatcher.addURI("com.example.contentprovider", "user", USER_MATCH_CODE);
    }

    @Override
    public boolean onCreate() {
        userDatabaseHelper = new UserDatabaseHelper(getContext());
        return false;
    }



    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int result = uriMatcher.match(uri);
        if (result == USER_MATCH_CODE) {
            SQLiteDatabase db = userDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query(Constants.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
//            db.close();
            return cursor;
        } else {
            throw new IllegalArgumentException("参数错误");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int result = uriMatcher.match(uri);
        if (result == USER_MATCH_CODE){
            SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
            long id = db.insert(Constants.TABLE_NAME, null, values);
            Log.d(TAG,"insert user ---->" + id);
            Uri resultUri = Uri.parse("content://com.example.contentprovider/user/" + id);
//            插入数据成功,数据已经发生了变化,所以通知其他地方(谁监听就通知谁)
            getContext().getContentResolver().notifyChange(resultUri,null);
            return resultUri;
        }else {
            throw new IllegalArgumentException("参数错误!");
        }
    }
//     Gradle project sync failed. Please fix your project and try again.

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
