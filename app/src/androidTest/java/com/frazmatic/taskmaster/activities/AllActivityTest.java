package com.frazmatic.taskmaster.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.frazmatic.taskmaster.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void allActivityTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.buttonSettings), withText("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editTextUserName),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Josh"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.buttonSaveSettings), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.action_bar),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.textMainTitle), withText("Josh's Tasks:"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView.check(matches(withText("Josh's Tasks:")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.textInputTaskTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Example Task"), closeSoftKeyboard());

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.textInputTaskDescription),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout3),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Sample Description"), closeSoftKeyboard());

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.textInputTaskTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Example Task 2"), closeSoftKeyboard());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.textInputTaskDescription),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout3),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("Description 2"), closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton5.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textViewSubmittedCount), withText("Tasks Submitted: 2"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(withText("Tasks Submitted: 2")));

        pressBack();

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        materialButton6.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.textInputTaskTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("Example Task 1"), closeSoftKeyboard());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.textInputTaskDescription),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout3),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("Sample Description"), closeSoftKeyboard());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton7.perform(click());

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.textInputTaskTitle),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("Example Task 2"), closeSoftKeyboard());

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.textInputTaskDescription),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.textInputLayout3),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText8.perform(replaceText("Sample Description 2"), closeSoftKeyboard());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.buttonAddTask), withText("Add Task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton8.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(androidx.appcompat.R.id.action_bar),
                                        childAtPosition(
                                                withId(androidx.appcompat.R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(withParent(allOf(withId(android.R.id.content),
                                withParent(withId(androidx.appcompat.R.id.decor_content_parent)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.textViewTaskFragTitle), withText("1. Example Task"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.recyclerTasks)))),
                        isDisplayed()));
        textView3.check(matches(withText("1. Example Task")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textViewTaskFragBody), withText("Sample Description"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.recyclerTasks)))),
                        isDisplayed()));
        textView4.check(matches(withText("Sample Description")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.textViewTaskFragTitle), withText("2. Example Task 2"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.recyclerTasks)))),
                        isDisplayed()));
        textView5.check(matches(withText("2. Example Task 2")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textViewTaskFragState), withText("New"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.recyclerTasks)))),
                        isDisplayed()));
        textView6.check(matches(withText("New")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerTasks),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textViewTaskDetailsTitle), withText("Example Task 2"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView7.check(matches(withText("Example Task 2")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.textViewDetailsBody), withText("Description 2"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView8.check(matches(withText("Description 2")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.textViewDetailsState), withText("New"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView9.check(matches(withText("New")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
