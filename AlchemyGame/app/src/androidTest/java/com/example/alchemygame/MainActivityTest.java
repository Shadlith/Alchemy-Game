package com.example.alchemygame;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor statsMonitor = getInstrumentation().addMonitor(StatsActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor storeMonitor = getInstrumentation().addMonitor(StoreActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor inventoryMonitor = getInstrumentation().addMonitor(InventoryActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor mapMonitor = getInstrumentation().addMonitor(LocationComponentActivity.class.getName(), null, false);
    Instrumentation.ActivityMonitor craftMonitor = getInstrumentation().addMonitor(Crafting.class.getName(), null, false);




    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testStatsButton() {
        assertNotNull(mActivity.findViewById(R.id.statsButton));

        onView(withId(R.id.statsButton)).perform(click());
        Activity statsActivity = getInstrumentation().waitForMonitorWithTimeout(statsMonitor, 3000);
        assertNotNull(statsActivity);
    }

    @Test
    public void testStoreButton() {
        assertNotNull(mActivity.findViewById(R.id.storeButton));

        onView(withId(R.id.storeButton)).perform(click());
        Activity storeActivity = getInstrumentation().waitForMonitorWithTimeout(storeMonitor, 3000);
        assertNotNull(storeActivity);
    }

    @Test
    public void testInventoryButton() {
        assertNotNull(mActivity.findViewById(R.id.inventoryButton));

        onView(withId(R.id.inventoryButton)).perform(click());
        Activity inventoryActivity = getInstrumentation().waitForMonitorWithTimeout(inventoryMonitor, 3000);
        assertNotNull(inventoryActivity);
    }

    @Test
    public void testMapButton() {
        assertNotNull(mActivity.findViewById(R.id.mapButton));

        onView(withId(R.id.mapButton)).perform(click());
        Activity mapActivity = getInstrumentation().waitForMonitorWithTimeout(mapMonitor, 3000);
        assertNotNull(mapActivity);
    }

    @Test
    public void testCraftButton() {
        assertNotNull(mActivity.findViewById(R.id.craftButton));

        onView(withId(R.id.craftButton)).perform(click());
        Activity craftActivity = getInstrumentation().waitForMonitorWithTimeout(craftMonitor, 3000);
        assertNotNull(craftActivity);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }
}