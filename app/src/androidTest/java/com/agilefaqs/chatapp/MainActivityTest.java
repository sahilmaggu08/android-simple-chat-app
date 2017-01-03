package com.agilefaqs.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

import com.confengine.chatapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MainActivityTest extends ActivityUnitTestCase<MainActivity>{

    public MainActivityTest() {
        super(MainActivity.class);
    }

    MainActivity activity = null;
    private EditText messageField;

    @Before
    protected void setup () throws Exception {
        super.setUp();
        activity = startActivity(new Intent(getInstrumentation().getTargetContext(), MainActivity.class), null, null);
        messageField=(EditText)activity.findViewById(R.id.messageInput);

    }


    public void testCheckClickableMessageField() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Context context = getInstrumentation().getTargetContext();
        context.setTheme(R.style.Theme_AppCompat);
        activity = launchActivity(context.getPackageName(),
                MainActivity.class, null);
        messageField=(EditText)activity.findViewById(R.id.messageInput);
        assertEquals(messageField.isClickable(), true);
    }

    public void testInputAfterEnterText() {
        Context context = getInstrumentation().getTargetContext();
        context.setTheme(R.style.Theme_AppCompat);
        activity = launchActivity(context.getPackageName(),
                MainActivity.class, null);
        messageField=(EditText)activity.findViewById(R.id.messageInput);
        messageField.setText("This is a New Message");
        assertEquals(messageField.isClickable(), true);
        assertEquals(messageField.isEnabled(), true);
    }

    public void testMessagesNullTest() {
        Context context = getInstrumentation().getTargetContext();
        context.setTheme(R.style.Theme_AppCompat);
        activity = launchActivity(context.getPackageName(),
                MainActivity.class, null);
        getInstrumentation().waitForIdleSync();
        Button sendButton = (Button) activity.findViewById(R.id.sendButton);
        activity.downloadMessages();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotNull(sendButton);
    }
    public void testZActivityLaunchedWithIntent() {

        final Intent launchIntent = getStartedActivityIntent();

        assertNotNull("Intent was null", launchIntent);

        assertTrue(isFinishCalled());
    }
    @After
    public void teardown() throws Exception {
        super.tearDown();
    }
}