package com.example.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an Order entity in the system.
 * Contains information about the order, including user, business, goods details, and related metadata.
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the order record.
     */
    private Integer id;

    /**
     * User ID associated with the order.
     */
    private Integer userId;

    /**
     * Business ID associated with the order.
     */
    private Integer businessId;

    /**
     * Goods ID associated with the order.
     */
    private Integer goodsId;

    /**
     * Unique identifier for the order (e.g., generated order number).
     */
    private String orderId;

    /**
     * Address ID where the order will be delivered.
     */
    private Integer addressId;

    /**
     * Quantity of goods in the order.
     */
    private Integer num;

    /**
     * Total price for the order.
     */
    private Double price;

    /**
     * Status of the order (e.g., Pending, Completed, Canceled).
     */
    private String status;

    /**
     * List of cart items associated with the order.
     */
    private List<Cart> cartData;

    /**
     * Name of the business associated with the order.
     */
    private String businessName;

    /**
     * Name of the goods in the order.
     */
    private String goodsName;

    /**
     * URL or path to the goods image.
     */
    private String goodsImg;

    /**
     * Unit of measurement for the goods (e.g., kg, piece).
     */
    private String goodsUnit;

    /**
     * Price per unit of the goods.
     */
    private Double goodsPrice;

    /**
     * Username of the user who placed the order.
     */
    private String username;

    /**
     * Delivery address of the user who placed the order.
     */
    private String useraddress;

    /**
     * Phone number of the user who placed the order.
     */
    private String phone;

    /**
     * Default constructor.
     */
    public Orders() {
    }

    // Getters and Setters with detailed comments

    /**
     * Retrieves the order ID.
     *
     * @return The order ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the order ID.
     *
     * @param id The order ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the user ID associated with the order.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the order.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the business ID associated with the order.
     *
     * @return The business ID.
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the business ID associated with the order.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * Retrieves the goods ID associated with the order.
     *
     * @return The goods ID.
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * Sets the goods ID associated with the order.
     *
     * @param goodsId The goods ID to set.
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Retrieves the name of the business.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the name of the business.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Retrieves the name of the goods.
     *
     * @return The goods name.
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the name of the goods.
     *
     * @param goodsName The goods name to set.
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * Retrieves the image URL or path of the goods.
     *
     * @return The goods image URL or path.
     */
    public String getGoodsImg() {
        return goodsImg;
    }

    /**
     * Sets the image URL or path of the goods.
     *
     * @param goodsImg The goods image URL or path to set.
     */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * Retrieves the goods unit.
     *
     * @return The goods unit.
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * Sets the goods unit.
     *
     * @param goodsUnit The goods unit to set.
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * Retrieves the price per unit of the goods.
     *
     * @return The goods unit price.
     */
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * Sets the price per unit of the goods.
     *
     * @param goodsPrice The goods unit price to set.
     */
    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    // Remaining Getters and Setters...

    // (For brevity, similar comments can be added for remaining getters and setters)
}
