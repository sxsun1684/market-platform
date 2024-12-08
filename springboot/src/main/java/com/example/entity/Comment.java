package com.example.entity;

import java.io.Serializable;

/**
 * Represents a comment entity in the system.
 * Contains information about comments made by users on goods or businesses.
 */
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the comment.
     */
    private Integer id;

    /**
     * ID of the user who made the comment.
     */
    private Integer userId;

    /**
     * ID of the business associated with the comment.
     */
    private Integer businessId;

    /**
     * ID of the goods associated with the comment.
     */
    private Integer goodsId;

    /**
     * Name of the business associated with the comment.
     */
    private String businessName;

    /**
     * Name of the goods associated with the comment.
     */
    private String goodsName;

    /**
     * URL or path to the user's avatar.
     */
    private String userAvatar;

    /**
     * Name of the user who made the comment.
     */
    private String userName;

    /**
     * Time when the comment was made.
     */
    private String time;

    /**
     * Content of the comment.
     */
    private String content;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the comment ID.
     *
     * @return The comment ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the comment ID.
     *
     * @param id The comment ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the user who made the comment.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who made the comment.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the ID of the business associated with the comment.
     *
     * @return The business ID.
     */
    public Integer getBusinessId() {
        return businessId;
    }

    /**
     * Sets the ID of the business associated with the comment.
     *
     * @param businessId The business ID to set.
     */
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    /**
     * Retrieves the ID of the goods associated with the comment.
     *
     * @return The goods ID.
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * Sets the ID of the goods associated with the comment.
     *
     * @param goodsId The goods ID to set.
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Retrieves the name of the business associated with the comment.
     *
     * @return The business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Sets the name of the business associated with the comment.
     *
     * @param businessName The business name to set.
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * Retrieves the name of the goods associated with the comment.
     *
     * @return The goods name.
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * Sets the name of the goods associated with the comment.
     *
     * @param goodsName The goods name to set.
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * Retrieves the time when the comment was made.
     *
     * @return The comment time.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time when the comment was made.
     *
     * @param time The comment time to set.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Retrieves the content of the comment.
     *
     * @return The comment content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     *
     * @param content The comment content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the URL or path to the user's avatar.
     *
     * @return The user's avatar URL or path.
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * Sets the URL or path to the user's avatar.
     *
     * @param userAvatar The user's avatar URL or path to set.
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * Retrieves the name of the user who made the comment.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the name of the user who made the comment.
     *
     * @param userName The user's name to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
