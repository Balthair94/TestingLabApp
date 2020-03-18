package com.baltazar.testinglabapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.IsNot.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @get:Rule
    val activityRue = ActivityTestRule(MainActivity::class.java)


    //@get:Rule
    //val permissionAccessFineLocation: GrantPermissionRule = GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun launchActivity() { activityRule.scenario }

    @Test
    fun loginButtonIsNotAvailable() {
        onView(withId(R.id.edit_text_email)).perform(ViewActions.typeText("balta@gmail"))
        onView(withId(R.id.edit_text_password)).perform(ViewActions.typeText("1234"))
        onView(withId(R.id.button_login)).check(matches(not(isEnabled())))
    }

    @Test
    fun loginButtonIsAvailable() {
        onView(withId(R.id.edit_text_email)).perform(ViewActions.typeText("balta@gmail.com"))
        onView(withId(R.id.edit_text_password)).perform(ViewActions.typeText("1234"))
        onView(withId(R.id.button_login)).check(matches(isEnabled()))
    }

    @Test
    fun shouldDisplayLoadingIndicator() {
        onView(withId(R.id.edit_text_email)).perform(ViewActions.typeText("balta@gmail.com"))
        onView(withId(R.id.edit_text_password)).perform(ViewActions.typeText("1234"))
        onView(withId(R.id.button_login)).perform(click())
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
    }

    /**
     * Really useful when we want to get resources
     */
    private fun getString(id: Int): String {
        val resources = ApplicationProvider.getApplicationContext<Context>().resources
        return resources.getString(id)
    }
}
