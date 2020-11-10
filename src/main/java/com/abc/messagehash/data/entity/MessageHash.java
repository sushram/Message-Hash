package com.abc.messagehash.data.entity;

import javax.persistence.*;

@Entity
@Table(name="MESSAGE_HASH")
public class MessageHash {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @Column(name="HASH", length = 255, nullable = false, unique = true)
    private String hash;

    @Column(name="MESSAGE", length = 255, nullable = false, unique = true)
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
