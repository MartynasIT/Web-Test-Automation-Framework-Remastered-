package com.automation.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class JsonReader {
    private String path;
    private JSONObject jsonObject;
    JSONParser parser;

    public JsonReader(String path) {
        this.path = path;
        parser = new JSONParser();
        if (path.contains(".json"))
            parseFromFile();
        else
            parseFromString();
    }

    public JsonReader parseFromFile() {
        try {
            jsonObject = ((JSONObject) parser.parse(SystemReader.readFile(path)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException("Problem reading JSON file");
        }
        return this;
    }

    public JsonReader parseFromString() {
        try {
            jsonObject = ((JSONObject) parser.parse(path));
        } catch (ParseException e) {
            throw new RuntimeException("Problem reading JSON string");
        }
        return this;
    }

    public JSONObject returnJsonObject() {
        return jsonObject;
    }

    public String getValue(String key) {
        if (key.contains("."))
            return extractFromJsonArray(key);

        return jsonObject.get(key).toString();
    }

    private String extractFromJsonArray(String key) {
        String[] parts = key.split("\\.");
        String array = parts[0];
        String item = parts[1];
        JSONObject results = (JSONObject) jsonObject.get(array);
        return results.get(item).toString();
    }

    public JSONArray getJsonArray(String value) {
        JSONObject object = parseFromFile().jsonObject;
        return (JSONArray) object.get(value);
    }

    public JsonNode getJsonMapper(String list) {
        JsonNode mapper = null;
        try {
            mapper = new ObjectMapper().readTree(getJsonArray(list).toJSONString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapper;
    }
}
