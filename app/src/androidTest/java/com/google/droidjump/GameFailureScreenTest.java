/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.droidjump;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.google.droidjump.GameConstants.FIRST_LEVEL_ID;

import android.content.Intent;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.google.droidjump.models.LevelManager;
import com.google.droidjump.models.NavigationHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameFailureScreenTest {
    private static final Intent MAIN_ACTIVITY_INTENT =
            new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activityTestRule.launchActivity(MAIN_ACTIVITY_INTENT);
        LevelManager.init(activityTestRule.getActivity());
        LevelManager.setCurrentLevelIndex(FIRST_LEVEL_ID);
        LevelManager.setLastLevelIndex(FIRST_LEVEL_ID);
        NavigationHelper.navigateToFragment(activityTestRule.getActivity(), new GameFailureFragment());
    }

    @Test
    public void checkIfFailTextDisplayed() {
        onView(withId(R.id.fail_text)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfRetryButtonIsDisplayed() {
        onView(withId(R.id.retry_button)).check(matches(isDisplayed()));
    }

    @Test
    public void checkOnClickMenuButton() {
        onView(withId(R.id.menu_button)).check(matches(isDisplayed()));
        onView(withId(R.id.menu_button)).perform(click());

        onView(withId(R.id.play_button)).check(matches(isDisplayed()));
    }

    @Test
    public void checkOnClickReplayButton() {
        LevelManager.init(activityTestRule.getActivity());

        onView(withId(R.id.retry_button))
                .perform(click());

        onView(withId(R.id.game_layout)).check(matches(isDisplayed()));
    }
}
