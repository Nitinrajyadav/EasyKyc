package com.nitin.ratify.repository.local;


import android.util.ArrayMap;


import com.nitin.ratify.repository.pojo.User;
import com.nitin.ratify.repository.remote.KEYS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nitin on 3/15/2017.
 */

public class LocalCache {

    private static LocalCache localCacheInstance;
    private static JSONParser mJSONParser;
    private User verifiedUser;

    private LocalCache() {
        verifiedUser = new User();
    }


    public static LocalCache getInstance() {
        if (localCacheInstance == null) localCacheInstance = new LocalCache();
        mJSONParser = new JSONParser();
        return localCacheInstance;
    }


    public void parseUserJson(JSONObject jsonObject) {

        try {
            JSONObject userObj = jsonObject.getJSONObject(KEYS.RESIDENT_DETAILS);
            verifiedUser = mJSONParser.parseUserJson(userObj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public User getVerifiedUser() {
        return verifiedUser;
    }

}
