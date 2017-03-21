package com.nitin.ekyc.repository.local;


import android.util.ArrayMap;

import com.nitin.ekyc.repository.pojo.Document;
import com.nitin.ekyc.repository.pojo.User;
import com.nitin.ekyc.repository.remote.KEYS;

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

    private ArrayList<Document> documentsList;
    private ArrayMap<String, Document> documentsArrayMap;


    private ArrayMap<String, String> departmentssArrayMap;


    private LocalCache() {
        departmentssArrayMap = new ArrayMap<>();
    }

    public static LocalCache getInstance() {
        if (localCacheInstance == null) localCacheInstance = new LocalCache();
        mJSONParser = new JSONParser();
        return localCacheInstance;
    }



    public void parseUserJson(JSONObject jsonObject) {
        User user = mJSONParser.parseUserJson(jsonObject);
        CurrentUserHolder currentUserHolder = CurrentUserHolder.getInstance();
        try {
            currentUserHolder.setmUser(user);
            currentUserHolder.setKYC_ID(jsonObject.getString(KEYS.JSON_ID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Document> getDocumentsList() {
        return documentsList;
    }

    public ArrayMap<String, Document> getDocumentsArrayMap() {
        return documentsArrayMap;
    }

    public void saveDocumentsFromJsonArray(JSONArray jsonArray) {
        documentsList = null;
        documentsArrayMap = null;
        documentsArrayMap = new ArrayMap<>();
        documentsList = new ArrayList<>();
        mJSONParser.parseDocumentsJsonArray(documentsList, documentsArrayMap, jsonArray);
    }

    public void saveDepartmentsFromJson(JSONObject jsonObject) {
        departmentssArrayMap = null;
        departmentssArrayMap = new ArrayMap<>();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray(KEYS.JSON_AUTHORITY);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject authorityObj = jsonArray.getJSONObject(i);
                departmentssArrayMap.put(authorityObj.getString(KEYS.JSON_AUTHORITY_ID),
                        authorityObj.getString(KEYS.JSON_AUTHORITY_NAME));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayMap<String, String> getDepartmentssArrayMap() {
        return departmentssArrayMap;
    }


}
