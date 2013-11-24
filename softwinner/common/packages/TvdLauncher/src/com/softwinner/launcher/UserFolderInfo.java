/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.softwinner.launcher;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Represents a folder containing shortcuts or apps.
 */
public class UserFolderInfo extends FolderInfo {
    /**
     * The apps and shortcuts 
     */
    ArrayList<ShortcutInfo> contents = new ArrayList<ShortcutInfo>();
    
    UserFolderInfo() {
        itemType = LauncherSettings.Favorites.ITEM_TYPE_USER_FOLDER;
    }
    
    /**
     * Add an app or shortcut
     * 
     * @param item
     */
    public void add(ShortcutInfo item) {
        contents.add(item);
    }
    
    /**
     * Remove an app or shortcut. Does not change the DB.
     * 
     * @param item
     */
    public void remove(ShortcutInfo item) {
        contents.remove(item);
    }
    
    @Override
    void onAddToDatabase(ContentValues values) { 
        super.onAddToDatabase(values);
        values.put(LauncherSettings.Favorites.TITLE, title.toString());
    }
}