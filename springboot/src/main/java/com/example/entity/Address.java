package com.example.entity;

import java.io.Serializable;

/**
 * Represents an address entity in the system.
 * Contains information about user addresses, including phone numbers and specific address details.
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the address record.
     */
    private Integer id;

    /**
     * ID of the user associated with the address.
     */
    private Integer userId;

    /**
     * Username of the user associated with the address.
     */
    private String username;

    /**
     * Detailed user address (e.g., street, city, state, etc.).
     */
    private String useraddress;

    /**
     * Phone number associated with the user or address.
     */
    private String phone;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the address record ID.
     *
     * @return The address ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the address record ID.
     *
     * @param id The address ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the user ID associated with the address.
     *
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the address.
     *
     * @param userId The user ID to set.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the username of the user associated with the address.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user associated with the address.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the detailed address.
     *
     * @return The detailed user address.
     */
    public String getUseraddress() {
        return useraddress;
    }

    /**
     * Sets the detailed address.
     *
     * @param useraddress The user address to set.
     */
    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    /**
     * Retrieves the phone number associated with the address.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number associated with the address.
     *
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
