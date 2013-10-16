package com.in6k.twitter.domain;

import java.util.Date;

public class Tweet {

    private Integer id;
    private User user;
    private Integer user_id;
    private String message;
    private Date dateAt;
    private String login;

    public Tweet(String login, Date dateAt, String message) {
        this.login = login;
        this.dateAt = dateAt;
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateAt() {
        return dateAt;
    }

    public void setDateAt(Date dateAt) {
        this.dateAt = dateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User author) {
        this.user = author;
    }
    public Tweet(){

    }
    /*public Tweet(String message, Timestamp dateAt, User author) {
        this.message = message;
        this.dateAt = dateAt;
        this.author = author;
    }*/
}
