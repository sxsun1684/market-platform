package com.example.entity;

import java.io.Serializable;

/**
 * Represents a product category in the system.
 * This class provides information about the category, including its name, description, and icon.
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the category.
     */
    private Integer id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * Description of the category.
     */
    private String description;

    /**
     * URL or path to the category icon.
     */
    private String img;

    /**
     * Retrieves the category ID.
     *
     * @return The category ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the category ID.
     *
     * @param id The category ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the category name.
     *
     * @return The category name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the category name.
     *
     * @param name The category name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the category description.
     *
     * @return The category description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the category description.
     *
     * @param description The category description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the URL or path to the category icon.
     *
     * @return The category icon URL or path.
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets the URL or path to the category icon.
     *
     * @param img The category icon URL or path to set.
     */
    public void setImg(String img) {
        this.img = img;
    }
}
