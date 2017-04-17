/**
 * Copyright Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.firebase.codelab.friendlychat;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class FriendlyMessage {

    private String id;
    private String text;
    private String name;
    private String photoUrl;
    public ArrayList<String> receiverIds;
    private double latitude;
    private double longitude;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl, ArrayList<String> receiverIds, Location location) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.receiverIds = receiverIds;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setReceiverId (ArrayList<String> receiverIds)
    {
        this.receiverIds = receiverIds;
    }

    public ArrayList<String> getReceiverId ()
    {
        return receiverIds;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
