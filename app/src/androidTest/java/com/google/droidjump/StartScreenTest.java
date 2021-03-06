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
import static org.junit.Assert.assertEquals;

import android.content.Intent;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.google.droidjump.models.LevelManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StartScreenTest {
    private static final Intent MAIN_ACTIVITY_INTENT =
            new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), MainActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activityTestRule.launchActivity(MAIN_ACTIVITY_INTENT);
    }

    @Test
    public void navigateToGameScreen() {
        onView(withId(R.id.play_button)).perform(click());

        onView(withId(R.id.game_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void navigateToLevelsScreen() {
        onView(withId(R.id.level_button)).perform(click());

        onView(withId(R.id.choose_level_header)).check(matches(isDisplayed()));
    }

    @Test
    public void startNewGame() {
        // Setting the random current level.
        int testingCurrentLevelIndex = LevelManager.getLastLevelIndex();
        LevelManager.setCurrentLevelIndex(testingCurrentLevelIndex);
        // Click on new game button.
        onView(ViewMatchers.withId(R.id.new_game_button)).perform(ViewActions.click());

        assertEquals(LevelManager.getCurrentLevelIndex(), FIRST_LEVEL_ID);
        assertEquals(LevelManager.getLastLevelIndex(), FIRST_LEVEL_ID);
    }

    @Test
    public void navigateToHowToPlayScreen() {
        onView(ViewMatchers.withId(R.id.how_to_play_button)).perform(ViewActions.click());

        onView(ViewMatchers.withId(R.id.how_to_play_title)).check(matches(isDisplayed()));
    }
}
