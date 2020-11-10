package com.abc.messagehash.buisness.domain;

public class MessageHashNotFoundException extends RuntimeException{
    private String digest;

    public MessageHashNotFoundException(String hash) {
        super("unable to find message");
        this.digest = hash;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getDigest() {
        return digest;
    }
}
