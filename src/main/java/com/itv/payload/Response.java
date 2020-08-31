package com.itv.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private final String message;

    @JsonCreator
    public Response(@JsonProperty("message") String message) {
        this.message = message;
    }
}
