package com.example.entity;

import java.io.Serializable;

/**
 * Product category information table
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;
    /** Category name */
    private String name;
    /** Category description */
    private String description;
    /** Category icon */
    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
