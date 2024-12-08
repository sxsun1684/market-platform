package com.example.entity;

import java.io.Serializable;

/**
 * Represents a shopping cart entity in the system.
 * Contains information about items added to the shopping cart by a user.
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the cart record.
     */
    private Integer id;

    /**
     * ID of the user who owns the cart.
     */
    private Integer userId;

    /**
     * ID of the business associated with the cart item.
     */
    private Integer businessId;

    /**
     * ID of the goods added to the cart.
     */
    private Integer goodsId;

    /**
     * Quantity of goods added to the cart.
     */
    private Integer num;

    /**
     * Name of the business associated with the cart item.
     */
    private String businessName;

    /**
     * Name of the goods added to the cart.
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
     * Price per unit of the goods.
     */
    private Double goodsPrice;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the cart record ID.
     *
     * @return The cart record ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the cart record ID.
     *
     * @param id The cart record ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the user ID who owns the cart.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID who owns the cart.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the business ID associated with the cart item.
     *
     * @return The business ID.
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the business ID associated with the cart item.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * Retrieves the goods ID added to the cart.
     *
     * @return The goods ID.
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * Sets the goods ID added to the cart.
     *
     * @param goodsId The goods ID to set.
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Retrieves the quantity of goods added to the cart.
     *
     * @return The quantity of goods.
     */
    public Integer getNum() {
        return num;
    }

    /**
     * Sets the quantity of goods added to the cart.
     *
     * @param num The quantity of goods to set.
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * Retrieves the name of the business associated with the cart item.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the name of the business associated with the cart item.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Retrieves the name of the goods added to the cart.
     *
     * @return The goods name.
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the name of the goods added to the cart.
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
     * Retrieves the price per unit of the goods.
     *
     * @return The goods price.
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Sets the price per unit of the goods.
     *
     * @param goodsPrice The goods price to set.
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
