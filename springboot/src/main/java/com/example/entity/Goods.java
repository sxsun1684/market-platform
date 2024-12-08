package com.example.entity;

import java.io.Serializable;

/**
 * Represents a goods entity in the system.
 * Contains information about the goods, such as name, description, price, and related metadata.
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the goods.
     */
    private Integer id;

    /**
     * Name of the goods.
     */
    private String name;

    /**
     * Description of the goods.
     */
    private String description;

    /**
     * URL or path to the main image of the goods.
     */
    private String img;

    /**
     * Price of the goods.
     */
    private Double price;

    /**
     * Unit of measurement for the goods (e.g., kg, piece).
     */
    private String unit;

    /**
     * Current stock or count of the goods.
     */
    private Integer count;

    /**
     * ID of the category/type the goods belong to.
     */
    private Integer typeId;

    /**
     * ID of the business associated with the goods.
     */
    private Integer businessId;

    /**
     * Name of the category/type the goods belong to (for display purposes).
     */
    private String typeName;

    /**
     * Name of the business associated with the goods (for display purposes).
     */
    private String businessName;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the goods ID.
     *
     * @return The goods ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the goods ID.
     *
     * @param id The goods ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the goods.
     *
     * @return The goods name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the goods.
     *
     * @param name The goods name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the description of the goods.
     *
     * @return The goods description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the goods.
     *
     * @param description The goods description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the URL or path to the main image of the goods.
     *
     * @return The goods image URL or path.
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets the URL or path to the main image of the goods.
     *
     * @param img The goods image URL or path to set.
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Retrieves the price of the goods.
     *
     * @return The goods price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the goods.
     *
     * @param price The goods price to set.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Retrieves the unit of the goods.
     *
     * @return The goods unit.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit of the goods.
     *
     * @param unit The goods unit to set.
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Retrieves the current stock or count of the goods.
     *
     * @return The goods count.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Sets the current stock or count of the goods.
     *
     * @param count The goods count to set.
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * Retrieves the ID of the category/type the goods belong to.
     *
     * @return The type ID.
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * Sets the ID of the category/type the goods belong to.
     *
     * @param typeId The type ID to set.
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * Retrieves the ID of the business associated with the goods.
     *
     * @return The business ID.
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the ID of the business associated with the goods.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * Retrieves the name of the category/type the goods belong to.
     *
     * @return The type name.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Sets the name of the category/type the goods belong to.
     *
     * @param typeName The type name to set.
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Retrieves the name of the business associated with the goods.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the name of the business associated with the goods.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
