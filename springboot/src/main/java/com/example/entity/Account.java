package com.example.entity;

/**
 * Base class representing a generic account in the system.
 * This class provides common attributes shared by different user roles (e.g., Admin, User, Business).
 */
public class Account {

    /**
     * Unique identifier for the account.
     */
    private Integer id;

    /**
     * Username used for authentication.
     */
    private String username;

    /**
     * Full name or display name of the account holder.
     */
    private String name;

    /**
     * Password for authentication.
     */
    private String password;

    /**
     * Role identifier for the account (e.g., ADMIN, USER, BUSINESS).
     */
    private String role;

    /**
     * New password for updating account credentials.
     */
    private String newPassword;

    /**
     * URL or path to the avatar image of the account holder.
     */
    private String avatar;

    /**
     * Authentication token associated with the account.
     */
    private String token;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the account ID.
     *
     * @return The account ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the account ID.
     *
     * @param id The account ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name of the account holder.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the account holder.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the account password.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the account password.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the role identifier of the account.
     *
     * @return The role identifier.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role identifier of the account.
     *
     * @param role The role identifier to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieves the new password for the account.
     *
     * @return The new password.
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the new password for the account.
     *
     * @param newPassword The new password to set.
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * Retrieves the avatar URL or path.
     *
     * @return The avatar URL or path.
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar URL or path.
     *
     * @param avatar The avatar URL or path to set.
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Retrieves the authentication token.
     *
     * @return The token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the authentication token.
     *
     * @param token The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
