package com.example.imagepicker;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import org.jetbrains.annotations.NotNull;

public class pickerActivity extends AppCompatActivity {

    private static final String TAG = pickerActivity.class.getName();
    private static final int LOADER_ID = 1;

    @SuppressLint("Range")
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
//        instance_id --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: duration --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: description --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: picasa_id --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: latitude --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: orientation --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: height --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: is_drm --- > 0
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: bucket_display_name --- > Download
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: owner_package_name --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: volume_name --- > external_primary
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: date_modified --- > 1658548356
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: date_expires --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: _display_name --- > num10.png
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: datetaken --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: mime_type --- > image/png
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: _id --- > 37
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: _data --- > /storage/emulated/0/Download/num10.png
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: _hash --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: _size --- > 50499
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: title --- > num10
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: width --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: longitude --- > null
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: is_trashed --- > 0
//        2022-07-23 14:22:46.184 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: group_id --- > 105179173
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: document_id --- > null
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: is_pending --- > 0
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: date_added --- > 1658549258
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: mini_thumb_magic --- > null
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: primary_directory --- > Download
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: secondary_directory --- > null
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: isprivate --- > null
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: original_document_id --- > null
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: bucket_id --- > 540528482
//        2022-07-23 14:22:46.185 3832-3832/com.example.imagepicker D/com.example.imagepicker.pickerActivity: relative_path --- > Download/
//
//
//        ContentResolver contentResolver = getContentResolver();
//        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//        Cursor query = contentResolver.query(uri, null, null, null, null);
//        String[] columnNames = query.getColumnNames();
//        while (query.moveToNext()) {
//            Log.d(TAG, "--------------------");
//            for (String columnName : columnNames) {
//                Log.d(TAG, columnName + " --- > " + query.getString(query.getColumnIndex(columnName)));
//            }
//            Log.d(TAG, "--------------------");
//        }
//        query.close();
        initLoadManager();
    }

    private void initLoadManager() {
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {

            @NonNull
            @NotNull
            @Override
            public Loader<Cursor> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
                if (id == LOADER_ID) {
                    return new CursorLoader(pickerActivity.this,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            new String[]{"_data", "_display_name", "date_added"},
                            null, null, "date_added DESC");
                }
                return null;
            }

            @SuppressLint("Range")
            @Override
            public void onLoadFinished(@NonNull @NotNull Loader<Cursor> loader, Cursor data) {
                if (data != null) {
                    String[] columnNames = data.getColumnNames();
                    while (data.moveToNext()) {
                        Log.d(TAG,"---------");
                        for (String columnName : columnNames) {
                            Log.d(TAG,columnName + " -- > " + data.getString(data.getColumnIndex(columnName)));
                        }
                        Log.d(TAG,"---------");
                    }
                    data.close();
                }
            }

            @Override
            public void onLoaderReset(@NonNull @NotNull Loader<Cursor> loader) {

            }
        });


    }
}

