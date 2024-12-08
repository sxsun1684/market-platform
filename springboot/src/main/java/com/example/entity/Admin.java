package com.example.entity;

import java.io.Serializable;

/**
 * Represents an administrator account in the system.
 * Extends the {@link Account} class to inherit common account attributes.
 * Contains additional attributes specific to administrators.
 */
public class Admin extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the administrator.
     */
    private Integer id;

    /**
     * Username used for administrator account authentication.
     */
    private String username;

    /**
     * Password used for administrator account authentication.
     */
    private String password;

    /**
     * Full name or display name of the administrator.
     */
    private String name;

    /**
     * Phone number associated with the administrator.
     */
    private String phone;

    /**
     * Email address associated with the administrator.
     */
    private String email;

    /**
     * URL or path to the avatar image of the administrator.
     */
    private String avatar;

    /**
     * Role identifier for the administrator account.
     */
    private String role;

    // Getters and Setters with detailed comments

    /**
     * Retrieves the administrator ID.
     *
     * @return The administrator ID.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the administrator ID.
     *
     * @param id The administrator ID to set.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the administrator.
     *
     * @return The administrator username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username The administrator username to set.
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of the administrator.
     *
     * @return The administrator password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password The administrator password to set.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the full name of the administrator.
     *
     * @return The administrator full name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the full name of the administrator.
     *
     * @param name The administrator full name to set.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the administrator.
     *
     * @return The administrator phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the administrator.
     *
     * @param phone The administrator phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the email address of the administrator.
     *
     * @return The administrator email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the administrator.
     *
     * @param email The administrator email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the avatar URL or path of the administrator.
     *
     * @return The avatar URL or path.
     */
    @Override
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the avatar URL or path of the administrator.
     *
     * @param avatar The avatar URL or path to set.
     */
    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Retrieves the role identifier of the administrator.
     *
     * @return The administrator role identifier.
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Sets the role identifier of the administrator.
     *
     * @param role The administrator role identifier to set.
     */
    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
