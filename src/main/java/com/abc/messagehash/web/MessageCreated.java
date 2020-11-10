package com.abc.messagehash.web;

public class MessageCreated {
    private final String digest;

    public MessageCreated(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }
}
