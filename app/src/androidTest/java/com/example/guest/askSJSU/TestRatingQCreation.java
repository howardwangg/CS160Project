package com.example.guest.askSJSU;

import android.support.test.espresso.Espresso;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.concurrent.ExecutionException;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@SmallTest
//White-Box
public class TestRatingQCreation {

    public static String USER = "testuser";
    public static String PW = "testpass";
    //Edit Question body (QBODY).
    public static String QBODY = "";

    @Rule
    public ActivityTestRule<InitialLoginActivity> Activity = new ActivityTestRule<>(InitialLoginActivity.class);

    @Test
    public void testPollingQCreation() throws ExecutionException, InterruptedException, JSONException {
        onView(withId(R.id.usernameEditText)).perform(typeText(USER));
        onView(withId(R.id.pwEditText)).perform(typeText(PW));
        onView(withId(R.id.pwEditText)).perform(closeSoftKeyboard());
        onView(withId(R.id.loginBtn)).perform(click());
        onView(withId(R.id.createBtn)).perform(click());
        onView(withId(R.id.questionTypeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Rating"))).perform(click());
        onView(withId(R.id.questionBodyEditText)).perform(typeText(QBODY));
        onView(withId(R.id.questionExpirationDateEditText)).perform(typeText("2018-08-24 01:32:36"));
        onView(withId(R.id.questionExpirationDateEditText)).perform(closeSoftKeyboard());
        onView(withId(R.id.createPostBtn)).perform(click());
        Assert.assertNotNull(onData(allOf(is(instanceOf(String.class)), is(QBODY))));
    }
}

