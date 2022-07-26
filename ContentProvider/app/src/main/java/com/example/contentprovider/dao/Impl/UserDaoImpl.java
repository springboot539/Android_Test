package com.example.contentprovider.dao.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.contentprovider.Utils.Constants;
import com.example.contentprovider.dao.IUserDao;
import com.example.contentprovider.db.UserDatabaseHelper;
import com.example.contentprovider.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    private final UserDatabaseHelper userDatabaseHelper;


    public UserDaoImpl(Context context) {
        userDatabaseHelper = new UserDatabaseHelper(context);
    }

    @Override
    public long addUser(User user) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        ContentValues values = user2Values(user);
        long result = db.insert(Constants.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    @Override
    public int delUserById(int id) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        int result = db.delete(Constants.TABLE_NAME, Constants.FIELD_ID, new String[]{id + ""});
        db.close();
        return result;
    }

    @Override
    public int updateUser(User user) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
//        根据Id来更新内容
        ContentValues values = user2Values(user);
        int result = db.update(Constants.TABLE_NAME, values, Constants.FIELD_ID, new String[]{user.get_id() + ""});
        db.close();
        return result;
    }

    @Override
    public User getUserById(int id) {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        String sql = "select * from " + Constants.TABLE_NAME + " where " + Constants.FIELD_ID + " = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{id + ""});
        User user = null;
        if (cursor.moveToNext()) {
            user = cursor2User(cursor);
        }
        db.close();
        return user;
    }


    @Override
    public List<User> listAllUser() {
        SQLiteDatabase db = userDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null, null);
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = cursor2User(cursor);
            users.add(user);
        }
        db.close();
        return users;
    }

    private ContentValues user2Values(User user) {
        ContentValues values = new ContentValues();
        values.put(Constants.FIELD_USER_NAME, user.getUserName());
        values.put(Constants.FIELD_SEX, user.getSex());
        values.put(Constants.FIELD_PASSWORD, user.getPassword());
        values.put(Constants.FIELD_AGE, user.getAge());
        return values;
    }

    private User cursor2User(Cursor cursor) {
        User user = new User();
        int userId = cursor.getInt(cursor.getColumnIndex(Constants.FIELD_ID));
        user.set_id(userId);

        String userName = cursor.getString(cursor.getColumnIndex(Constants.FIELD_USER_NAME));
        user.setUserName(userName);

        String userPassword = cursor.getString(cursor.getColumnIndex(Constants.FIELD_PASSWORD));
        user.setPassword(userPassword);

        String userSex = cursor.getString(cursor.getColumnIndex(Constants.FIELD_SEX));
        user.setSex(userSex);

        int userAge = cursor.getInt(cursor.getColumnIndex(Constants.FIELD_AGE));
        user.setAge(userAge);
        return user;
    }
}
