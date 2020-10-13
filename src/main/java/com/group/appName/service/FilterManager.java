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

    public String filterByParams (String fileName, String params){
        JSONArray dataFromFile = getDataFromFile(fileName);
        JSONObject jsonObject = new JSONObject(params);
        ArrayList<String> filterFields = new ArrayList<>(jsonObject.keySet());

        for (String f : filterFields) {
            ArrayList<JSONObject> finalArray = filterByField(dataFromFile, params, f);
            dataFromFile = new JSONArray(finalArray);
        }
        return dataFromFile.toString();
    }

    private JSONArray getDataFromFile(String fileName) {
        String data = fireService.getFile(fileName);
        return new JSONArray(data);
    }

    private ArrayList<JSONObject> filterByField (JSONArray dataFromDB, String params, String filterField) {
        ArrayList<JSONObject> filteredList = new ArrayList<>();
        List<String> filterFieldList = getFieldList(params, filterField);
        if(filterFieldList.size() > 0) {
            for (String f : filterFieldList) {
                for (int m = 0; m < dataFromDB.length(); m++) {
                    JSONObject obj = dataFromDB.getJSONObject(m);
                    String StringFromBD = obj.getString(filterField);
                    if (StringFromBD.equals(f)) {
                        if (!filteredList.contains(obj)) {
                            filteredList.add(obj);
                        }
                    }
                }
            }
        } else {
            for(int i = 0; i < dataFromDB.length(); i++){
                filteredList.add(dataFromDB.getJSONObject(i));
            }
        }
        return filteredList;
    }

    private List<String> getFieldList(String params, String filterField) {
        JsonObject jsonObject = new Gson().fromJson(params, JsonObject.class);
        JsonArray jsonArray = isArray(jsonObject, filterField);
        String[] array = new Gson().fromJson(jsonArray, String[].class);
        return Arrays.asList(array);
    }

    private JsonArray isArray(JsonObject jsonObject, String filterField){
        JsonArray jsonArray = new JsonArray();
        JsonElement element = jsonObject.get(filterField);
        if (!element.isJsonArray()){
            String s = element.getAsString();
            jsonArray.add(s);
        } else {
            jsonArray = jsonObject.getAsJsonArray(filterField);
        }
        return jsonArray;
    }
}

