package com.group.appName.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FilterManager {

    @Autowired
    private FireService fireService;

    public String filterOne (String fileName, String params){
        String info = fireService.getFile(fileName);
        JSONArray infoFromDB = new JSONArray(info);
        JSONObject jsonObject = new JSONObject(params);
        ArrayList<String> fields = new ArrayList<>(jsonObject.keySet());

        for (int i = 0; i < fields.size(); i++) {
            ArrayList<JSONObject> finalArray = filterByFlag(infoFromDB, params, fields.get(i));
            infoFromDB = new JSONArray(finalArray);
        }
        return infoFromDB.toString();
    }

    private List<String> getFlagsList(String params, String field) {
        JsonObject jsonObject = new Gson().fromJson(params, JsonObject.class);
        JsonArray jsonArray = isArray(jsonObject, field);
        String[] arr = new Gson().fromJson(jsonArray, String[].class);
        return Arrays.asList(arr);
    }

     private JsonArray isArray(JsonObject jsonObject, String field){
        JsonArray jsonArray = new JsonArray();
        JsonElement element = jsonObject.get(field);
        if (!element.isJsonArray()){
            String s = element.getAsString();
            jsonArray.add(s);
        } else {
            jsonArray = jsonObject.getAsJsonArray(field);
        }
        return jsonArray;
    }

    private ArrayList<JSONObject> filterByFlag (JSONArray infoFromDB, String params, String field) {
        ArrayList<JSONObject> filtered = new ArrayList<>();
        List<String> flagsList = getFlagsList(params, field);
        for (int k = 0; k < flagsList.size(); k++) {
            for (int m = 0; m < infoFromDB.length(); m++) {
                JSONObject obj = infoFromDB.getJSONObject(m);
                String StringFromBD = obj.getString(field);
                String etalonFromClient = flagsList.get(k);
                if (StringFromBD.equals(etalonFromClient)) {
                    if (!filtered.contains(obj)) {
                        filtered.add(obj);
                    }
                }
            }
        }
        return filtered;
    }
}

