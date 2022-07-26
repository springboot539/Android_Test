package com.example.contentprovider;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.contentprovider.dao.IUserDao;
import com.example.contentprovider.dao.Impl.UserDaoImpl;
import com.example.contentprovider.pojo.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String TAG = "ExampleInstrumentedTest";

    @Test
    public void testAddUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        IUserDao userDao = new UserDaoImpl(appContext);
        User user = new User();
        user.setSex("male");
        user.setAge(22);
        user.setUserName("pangjiaming");
        user.setPassword("123456");
        long result = userDao.addUser(user);
        Log.d(TAG, "add user result  ---->" + user);
        assertNotEquals(-1, result);
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.contentprovider", appContext.getPackageName());
    }

    @Test
    public void testListAllUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        IUserDao userDao = new UserDaoImpl(appContext);
        List<User> users = userDao.listAllUser();
        Log.d(TAG, "users length --- > " + users.size());
        for (User user : users) {
            Log.d(TAG, "user --- > " + user);
        }
    }
}
