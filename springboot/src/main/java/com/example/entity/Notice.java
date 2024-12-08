package com.example.entity;

import java.io.Serializable;

/**
 * Represents a notice in the system.
 * Contains information about the notice, such as its title, content, creation time, and creator.
 */
public class Notice implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the notice.
     */
    private Integer id;

    /**
     * Title of the notice.
     */
    private String title;

    /**
     * Content or body of the notice.
     */
    private String content;

    /**
     * Creation time of the notice.
     */
    private String time;

    /**
     * Creator of the notice.
     */
    private String user;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the ID of the notice.
     *
     * @return The notice ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the notice.
     *
     * @param id The notice ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the notice.
     *
     * @return The notice title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notice.
     *
     * @param title The notice title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the content of the notice.
     *
     * @return The notice content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the notice.
     *
     * @param content The notice content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the creation time of the notice.
     *
     * @return The notice creation time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the creation time of the notice.
     *
     * @param time The notice creation time to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Retrieves the creator of the notice.
     *
     * @return The notice creator.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the creator of the notice.
     *
     * @param user The notice creator to set.
     */
    public void setUser(String user) {
        this.user = user;
    }
}
