package com.abc.messagehash.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    @JsonProperty("error")
    private final String error;

    @JsonProperty(value="message_sha256")
    private final String digest;


    public ErrorMessage(String error, String digest) {
        this.error = error;
        this.digest = digest;
    }
}
