package com.group.appName.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FilterManager {

    @Autowired
    private FileService fileService;

    public String filteringByAllSelectedFilters (String fileName, String allFilters) throws IOException {
        JSONArray listOfAlarms = convertFileDataFromStringToJSONArray(fileName); //Get list of cases that fireBrigade
        ObjectMapper mapper = new ObjectMapper();                                                        //responded to. 1 case = 1 table row

        HashMap map = (HashMap) mapper.readValue(allFilters, Map.class);

        JSONObject jsonObjectWithAllSelectedFilters = new JSONObject(map);//There are (In this object) keys (selected


                                                                           //table columns) and selected values in this column).

        ArrayList<String> selectedFilterCategories = new ArrayList<>//Table columns which was selected as filters by user.
                (jsonObjectWithAllSelectedFilters.keySet());        //1 column = 1 category
        JSONArray filteredListOfAlarms = filteringByOneCategory
                (selectedFilterCategories, listOfAlarms, allFilters);


        return filteredListOfAlarms.toString();
    }

    private JSONArray filteringByOneCategory (ArrayList<String> selectedFilterCategories, //Go thought the list of
            JSONArray listOfAlarms, String allSelectedFilters) {                          //categories and send each
                                                                              //category to
                                                                         //filteringByEachFilterFromOneCategory method
        for (String filtersCategory : selectedFilterCategories) {                         //to get finally filtered list
            ArrayList<JSONObject> finalArray = filteringByEachFilterFromOneCategory       //from it.
                    (listOfAlarms, allSelectedFilters, filtersCategory);
            listOfAlarms = new JSONArray(finalArray);
        }
        return listOfAlarms;
    }

    private JSONArray convertFileDataFromStringToJSONArray(String fileName) {
        String data = fileService.getFile(fileName);
        return new JSONArray(data);
    }

    private ArrayList<JSONObject> filteringByEachFilterFromOneCategory                    //Go thought all table rows and
            (JSONArray listOfAlarms, String allSelectedFilters, String filtersCategory) { //add alarm (row) to the returned
                                                                                          //list only if finds value
                ArrayList<JSONObject> filteredList = new ArrayList<>();                   //that equals to passed filter.
                List<String> filtersListFromOneCategory = getFiltersListFromOneCategory
                                                (allSelectedFilters, filtersCategory);
                if(filtersListFromOneCategory.size() > 0) {
                    for (String oneFilter : filtersListFromOneCategory) {
                        for (int m = 0; m < listOfAlarms.length(); m++) {
                            JSONObject oneAlarm = listOfAlarms.getJSONObject(m);
                            String cellFromTableRow = oneAlarm.getString(filtersCategory);
                            if (cellFromTableRow.equals(oneFilter)) {
                                if (!filteredList.contains(oneAlarm)) {
                                    filteredList.add(oneAlarm);
                                }
                            }
                        }
                    }
                } else {
                    for(int i = 0; i < listOfAlarms.length(); i++){
                        filteredList.add(listOfAlarms.getJSONObject(i));
                    }
                }

                return filteredList;
    }

    private List<String> getFiltersListFromOneCategory (String allSelectedFilters, String filtersCategory) {
        //Get list of selected filters
        JsonObject jsonObjectWithAllFilters = new Gson().fromJson(allSelectedFilters, JsonObject.class);     //which belong to the passed category.
        JsonArray arrayOfFilters = makeArrayOfFilters(jsonObjectWithAllFilters, filtersCategory);
        String[] array = new Gson().fromJson(arrayOfFilters, String[].class);
        return Arrays.asList(array);
    }

    private JsonArray makeArrayOfFilters(JsonObject jsonObjectWithAllFilters, String filtersCategory){   //Check whether user
        JsonArray arrayOfFilters = new JsonArray();                                                      //has selected more than one
        JsonElement element = jsonObjectWithAllFilters.get(filtersCategory);                             //filter in passed category.
        if (!element.isJsonArray()){                                                                     //And make array of filters
            String oneFilter = element.getAsString();                                                    //according to it from passed
            arrayOfFilters.add(oneFilter);                                                               //jsonObject.
        } else {
            arrayOfFilters = jsonObjectWithAllFilters.getAsJsonArray(filtersCategory);
        }
        return arrayOfFilters;
    }
}