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

    public String filteringByAllSelectedFilters(String fileName, String allFilters) throws IOException {
        JSONArray listOfAlarms = convertFileDataFromStringToJSONArray(fileName);
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = (HashMap) mapper.readValue(allFilters, Map.class);
        JSONObject jsonObjectWithAllSelectedFilters = new JSONObject(map);
        ArrayList<String> selectedFilterCategories = new ArrayList<>
                (jsonObjectWithAllSelectedFilters.keySet());
        JSONArray filteredListOfAlarms = filteringByOneCategory
                (selectedFilterCategories, listOfAlarms, allFilters);

        return filteredListOfAlarms.toString();
    }

    private JSONArray filteringByOneCategory(ArrayList<String> selectedFilterCategories,
                                             JSONArray listOfAlarms, String allSelectedFilters) {
        for (String filtersCategory : selectedFilterCategories) {
            ArrayList<JSONObject> finalArray = filteringByEachFilterFromOneCategory
                    (listOfAlarms, allSelectedFilters, filtersCategory);
            listOfAlarms = new JSONArray(finalArray);
        }
        return listOfAlarms;
    }

    private JSONArray convertFileDataFromStringToJSONArray(String fileName) {
        String data = fileService.getFile(fileName);
        return new JSONArray(data);
    }

    private ArrayList<JSONObject> filteringByEachFilterFromOneCategory
            (JSONArray listOfAlarms, String allSelectedFilters, String filtersCategory) {

        ArrayList<JSONObject> filteredList = new ArrayList<>();
        List<String> filtersListFromOneCategory = getFiltersListFromOneCategory
                (allSelectedFilters, filtersCategory);
        if (filtersListFromOneCategory.size() > 0) {
            filtersListFromOneCategory.forEach(oneFilter -> {
                for (int m = 0; m < listOfAlarms.length(); m++) {
                    JSONObject oneAlarm = listOfAlarms.getJSONObject(m);
                    String cellFromTableRow = oneAlarm.getString(filtersCategory);
                    if (cellFromTableRow.equals(oneFilter)) {
                        if (!filteredList.contains(oneAlarm)) {
                            filteredList.add(oneAlarm);
                        }
                    }
                }
            });
        } else {
            for (int i = 0; i < listOfAlarms.length(); i++) {
                filteredList.add(listOfAlarms.getJSONObject(i));
            }
        }

        return filteredList;
    }

    private List<String> getFiltersListFromOneCategory(String allSelectedFilters, String filtersCategory) {
        JsonObject jsonObjectWithAllFilters = new Gson().fromJson(allSelectedFilters, JsonObject.class);
        JsonArray arrayOfFilters = makeArrayOfFilters(jsonObjectWithAllFilters, filtersCategory);
        String[] array = new Gson().fromJson(arrayOfFilters, String[].class);
        return Arrays.asList(array);
    }

    private JsonArray makeArrayOfFilters(JsonObject jsonObjectWithAllFilters, String filtersCategory) {
        JsonArray arrayOfFilters = new JsonArray();
        JsonElement element = jsonObjectWithAllFilters.get(filtersCategory);
        if (!element.isJsonArray()) {
            String oneFilter = element.getAsString();
            arrayOfFilters.add(oneFilter);
        } else {
            arrayOfFilters = jsonObjectWithAllFilters.getAsJsonArray(filtersCategory);
        }
        return arrayOfFilters;
    }
}