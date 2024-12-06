package com.example.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Represents the Order information entity.
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Order ID */
    private Integer id;
    /** User ID associated with the order */
    private Integer userId;
    /** Business ID associated with the order */
    private Integer businessId;
    /** Goods ID associated with the order */
    private Integer goodsId;
    /** Unique identifier for the order */
    private String orderId;
    /** Address ID associated with the order */
    private Integer addressId;
    /** Quantity of goods ordered */
    private Integer num;
    /** Total price of the order */
    private Double price;
    /** Status of the order */
    private String status;

    /** List of cart items related to the order */
    private List<Cart> cartData;

    /** Name of the business associated with the order */
    private String businessName;
    /** Name of the goods associated with the order */
    private String goodsName;
    /** Image of the goods */
    private String goodsImg;
    /** Unit of the goods */
    private String goodsUnit;
    /** Price per unit of the goods */
    private Double goodsPrice;
    /** Username of the user who placed the order */
    private String username;
    /** Address of the user who placed the order */
    private String useraddress;
    /** Phone number of the user who placed the order */
    private String phone;

    public Orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Cart> getCartData() {
        return cartData;
    }

    public void setCartData(List<Cart> cartData) {
        this.cartData = cartData;
    }
}
