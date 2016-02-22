package com.sarahehabm.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

/**
 * Created by Sarah E. Mostafa on 20-Feb-16.
 */
public class ButtonClickTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private MainActivity mainActivity;
    private Button button;
    
    public ButtonClickTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);
        mainActivity = getActivity();
        button = (Button) mainActivity.findViewById(R.id.button_tell_joke);
    }

    @MediumTest
    public void testAsyncTask() {
        TouchUtils.clickView(this, button);
        assertNotSame("", mainActivity.joke);
    }
}
