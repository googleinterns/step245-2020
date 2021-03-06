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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.droidjump.leveldata.LevelType;
import com.google.droidjump.models.LevelManager;
import com.google.droidjump.models.NavigationHelper;

/**
 * Displays Levels Screen.
 */
public class LevelsFragment extends Fragment {
    private MainActivity activity;
    private LevelsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (MainActivity) getActivity();
        adapter = new LevelsAdapter(activity, LevelManager.getGameLevels(), LevelManager.getLastLevelIndex());
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.levels_screen, container,
                /* attachToRoot= */ false);
        // Finding gridView and putting levels to it.
        GridView gridView = rootView.findViewById(R.id.levels_grid_view);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((adapterView, view, levelIndex, ignored) -> {
            if (LevelManager.getLastLevelIndex() >= levelIndex || LevelManager.getGameLevels().get(levelIndex).getLevelType() == LevelType.INFINITE) {
                LevelManager.setCurrentLevelIndex(levelIndex);
                NavigationHelper.navigateToFragment(activity, new GameFragment());
            }
        });
        rootView.findViewById(R.id.menu_button).setOnClickListener(view -> activity.onBackPressed());
        NavigationHelper.addOnBackPressedEventListener(activity, new StartFragment());
        return rootView;
    }
}
