package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static android.test.MoreAsserts.assertNotEqual;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by elshaarawy on 17-May-17.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class JokeTaskTest {

    private boolean mIsSuccess;
    private String mData;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testJokeTaskSuccess() throws InterruptedException {

        new JokeTask(mActivityRule.getActivity(), new JokeTaskListener() {
            @Override
            public void result(boolean isSuccess, String data) {
              mIsSuccess = isSuccess;
            }
        }).execute();
        Thread.sleep(7000);
        assertFalse(mIsSuccess);
    }

    @Test
    public void testJokeTaskData() throws InterruptedException {

        new JokeTask(mActivityRule.getActivity(), new JokeTaskListener() {
            @Override
            public void result(boolean isSuccess, String data) {
                mData=data;
            }
        }).execute();
        Thread.sleep(7000);
        assertNotNull(mData);

    }

}
