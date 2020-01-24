package com.group.appName;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConvertToJsonString {
    public static String convertToJsonString(List<Fires> fires) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";

        try {
            return mapper.writeValueAsString(fires);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
}
