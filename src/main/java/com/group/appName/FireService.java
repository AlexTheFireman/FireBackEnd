package com.group.appName;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class FireService {

    Map<String, JSONObject> map = new HashMap<String, JSONObject>();

    public void addNewFire(File file) throws IOException, JSONException {
        String s = Convert.start(file.getPath());
        String name = file.getName();
        JSONObject json = new JSONObject(s);

        map.put(name, json);
    }

    public JSONObject getFire(String name){
        return map.get(name);
    }





}
