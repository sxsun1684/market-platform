package com.example.entity;

import java.io.Serializable;

/**
 * Notice information table
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** Title */
    private String title;
    /** Content */
    private String content;
    /** Creation time */
    private String time;
    /** Creator */
    private String user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
