package com.jss.camel.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.camel.util.json.JsonObject;

public class PrescriberDto implements Serializable {
    static int count = 1;

    @JsonProperty("id")
    private int id = count++;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonIgnore
    public JsonObject getDetails() {
        JsonObject data = new JsonObject();
        data.put("firstName", this.firstName);
        data.put("lastName", this.lastName);
        data.put("id", this.id);
        return data;
    }
}
