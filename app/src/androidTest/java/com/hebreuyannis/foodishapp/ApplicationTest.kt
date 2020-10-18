package com.hebreuyannis.foodishapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.hebreuyannis.foodishapp.app.foodish.FoodishActivity
import org.junit.Test

class ApplicationTest {

    @Test
    fun runApp() {
        ActivityScenario.launch(FoodishActivity::class.java)

        // Should display Foodish and Button must be clickable
        Espresso.onView(ViewMatchers.withText("Foodish"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.fav)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withText("you don't have favorite picture here"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.pressBackUnconditionally()
        Espresso.onView(withId(R.id.resfreshButton)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.favButton)).perform(ViewActions.click())

    }
}
