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

package com.google.droidjump.leaderboards_data;

/**
 * Represents a Leaderboard.
 */
public class Leaderboard {
    private String name;
    private int avatar;
    private String leaderboardID;

    public Leaderboard(String name, int avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public Leaderboard(String name, int avatar, String leaderboardID) {
        this.name = name;
        this.avatar = avatar;
        this.leaderboardID = leaderboardID;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getLeaderboardID() {
        return leaderboardID;
    }
}
