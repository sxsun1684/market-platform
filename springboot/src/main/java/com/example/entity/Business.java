package com.example.entity;

import java.io.Serializable;

/**
 * Represents a business entity in the system.
 * Extends the {@link Account} class to inherit common account attributes.
 * Contains additional attributes specific to a business.
 */
public class Business extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the business.
     */
    private Integer id;

    /**
     * Username used for business account authentication.
     */
    private String username;

    /**
     * Password used for business account authentication.
     */
    private String password;

    /**
     * Full name or display name of the business.
     */
    private String name;

    /**
     * Phone number associated with the business.
     */
    private String phone;

    /**
     * Email address associated with the business.
     */
    private String email;

    /**
     * URL or path to the avatar image of the business.
     */
    private String avatar;

    /**
     * Role identifier for the business account.
     */
    private String role;

    /**
     * Description or overview of the business.
     */
    private String description;

    /**
     * Status of the business account (e.g., active, inactive, under review).
     */
    private String status;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the business ID.
     *
     * @return The business ID.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the business ID.
     *
     * @param id The business ID to set.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the business.
     *
     * @return The business username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the business.
     *
     * @param username The business username to set.
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the business.
     *
     * @return The business password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the business.
     *
     * @param password The business password to set.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the full name of the business.
     *
     * @return The business full name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the business.
     *
     * @param name The business full name to set.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the business.
     *
     * @return The business phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the business.
     *
     * @param phone The business phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of the business.
     *
     * @return The business email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the business.
     *
     * @param email The business email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the avatar URL or path of the business.
     *
     * @return The avatar URL or path.
     */
    @Override
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar URL or path of the business.
     *
     * @param avatar The avatar URL or path to set.
     */
    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Retrieves the role identifier of the business.
     *
     * @return The business role identifier.
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Sets the role identifier of the business.
     *
     * @param role The business role identifier to set.
     */
    @Override
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieves the description of the business.
     *
     * @return The business description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the business.
     *
     * @param description The business description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the status of the business account.
     *
     * @return The business account status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the business account.
     *
     * @param status The business account status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
