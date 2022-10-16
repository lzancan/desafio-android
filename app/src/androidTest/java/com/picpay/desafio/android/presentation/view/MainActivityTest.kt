package com.picpay.desafio.android.presentation.view

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.domain.usecase.FakeGetUsersUseCase
import com.picpay.desafio.android.R
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.util.Result
import com.picpay.desafio.android.util.ResultError
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val users = listOf(
        User(
            id = 1001,
            name = "Eduardo Santos",
            img = "https://randomuser.me/api/portraits/men/9.jpg",
            username = "@eduardo.santos"
        )
    )
    @BindValue
    @JvmField
    val usecase = FakeGetUsersUseCase()


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldDisplayTitle() {
        usecase.result = Result.Success(users)

        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)

            moveToState(Lifecycle.State.RESUMED)

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun whenSuccess_showListItem() {
        usecase.result = Result.Success(users)

        launchActivity<MainActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("Eduardo Santos")
            )
            RecyclerViewMatchers.checkRecyclerViewItem(
                R.id.recyclerView,
                0,
                withText("@eduardo.santos")
            )
        }
    }

    @Test
    fun whenError_hideList() {
        usecase.result = Result.Error(ResultError.Error)

        launchActivity<MainActivity>().apply {
            onView(withId(R.id.recyclerView))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        }
    }
}