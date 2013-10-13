package com.in6k.twitter.primaryClasses;

import java.sql.Timestamp;

public class Tweet {

    private Integer id;

    private String message;

    private Timestamp dateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDateAt() {
        return dateAt;
    }

    public void setDateAt(Timestamp dateAt) {
        this.dateAt = dateAt;
    }
}
