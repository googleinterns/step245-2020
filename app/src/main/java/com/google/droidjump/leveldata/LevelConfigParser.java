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

package com.google.droidjump.leveldata;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class LevelConfigParser {
    final static String LEVELS_KEY = "levels";
    final static String RESOURCE_NAME_KEY = "resourceName";
    final static String LEVEL_NAME_KEY = "levelName";
    final static String LEVEL_TYPE_KEY = "LevelType";

    private ArrayList<LevelConfig> levelConfigs;

    public ArrayList<LevelConfig> getLevelConfigsFromResource(int fileId, Context context){
        levelConfigs = new ArrayList();
        getDataFromFile(fileId, context);
        return levelConfigs;
    }

    private void getDataFromFile(int fileId, Context context){
        JSONObject configsData = JSONReader.getJSONObjectFromResource(fileId, context.getResources());
        try {
            JSONArray levels = configsData.getJSONArray(LEVELS_KEY);
            for (int i = 0; i < levels.length(); i++) {
                JSONObject level = levels.getJSONObject(i);
                String levelName = level.getString(LEVEL_NAME_KEY);
                String resourceName = level.getString(RESOURCE_NAME_KEY);
                LevelType levelType = Enum.valueOf(LevelType.class, level.getString(LEVEL_TYPE_KEY));
                levelConfigs.add(new LevelConfig(resourceName, levelType, levelName, context));
            }
        } catch (JSONException e) {
            Log.e("LevelConfigParser", "Failed to parse JSON: " + e.getMessage());
        }
    }
}
