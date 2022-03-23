package com.jss.camel.dto;

import java.io.Serializable;

import org.apache.camel.util.json.JsonObject;

public class GreetingDto implements Serializable {
    static int count = 1;
    private int id = count++;
    private String message;

    public void setMessage(String msg) {
        message = msg;
    }

    public JsonObject getMessage() {
        JsonObject json = new JsonObject();
        json.put("id", id);
        json.put("text", message);
        return json;
    }
}
