package com.example.alchemygame;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class StatsActivityTest {

    @Rule
    public ActivityTestRule<StatsActivity> mActivityTestRule = new ActivityTestRule<StatsActivity>(StatsActivity.class);

    private StatsActivity statsActivity = null;

    @Before
    public void setUp() throws Exception {
        statsActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = statsActivity.findViewById(R.id.Cash_value);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        statsActivity = null;
    }
}