package com.example.entity;

import java.io.Serializable;

/**
 * Represents a favourite or collected item in the system.
 * Contains information about goods or businesses marked as favourites by users.
 */
public class Collect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the favourite record.
     */
    private Integer id;

    /**
     * ID of the user who marked the item as favourite.
     */
    private Integer userId;

    /**
     * ID of the business associated with the favourite item.
     */
    private Integer businessId;

    /**
     * ID of the goods marked as favourite.
     */
    private Integer goodsId;

    /**
     * Name of the business associated with the favourite item.
     */
    private String businessName;

    /**
     * Name of the goods marked as favourite.
     */
    private String goodsName;

    /**
     * URL or path to the image of the goods.
     */
    private String goodsImg;

    /**
     * Unit of measurement for the goods (e.g., kg, piece).
     */
    private String goodUnit;

    /**
     * Price of the goods marked as favourite.
     */
    private Double goodsPrice;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the ID of the favourite record.
     *
     * @return The favourite record ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the favourite record.
     *
     * @param id The favourite record ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the user ID who marked the item as favourite.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID who marked the item as favourite.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the business ID associated with the favourite item.
     *
     * @return The business ID.
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the business ID associated with the favourite item.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * Retrieves the goods ID marked as favourite.
     *
     * @return The goods ID.
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * Sets the goods ID marked as favourite.
     *
     * @param goodsId The goods ID to set.
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Retrieves the name of the business associated with the favourite item.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the name of the business associated with the favourite item.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Retrieves the name of the goods marked as favourite.
     *
     * @return The goods name.
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the name of the goods marked as favourite.
     *
     * @param goodsName The goods name to set.
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * Retrieves the URL or path to the image of the goods.
     *
     * @return The goods image URL or path.
     */
    public String getGoodsImg() {
        return goodsImg;
    }

    /**
     * Sets the URL or path to the image of the goods.
     *
     * @param goodsImg The goods image URL or path to set.
     */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * Retrieves the unit of measurement for the goods.
     *
     * @return The goods unit.
     */
    public String getGoodUnit() {
        return goodUnit;
    }

    /**
     * Sets the unit of measurement for the goods.
     *
     * @param goodUnit The goods unit to set.
     */
    public void setGoodUnit(String goodUnit) {
        this.goodUnit = goodUnit;
    }

    /**
     * Retrieves the price of the goods marked as favourite.
     *
     * @return The goods price.
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Sets the price of the goods marked as favourite.
     *
     * @param goodsPrice The goods price to set.
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
