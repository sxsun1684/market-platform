package com.example.entity;

import java.io.Serializable;

/**
 * Represents a user in the system.
 * Extends the {@link Account} class and adds additional user-specific attributes.
 */
public class User extends Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the user.
     */
    private Integer id;

    /**
     * Username used for authentication.
     */
    private String username;

    /**
     * Password used for authentication.
     */
    private String password;

    /**
     * Nickname or display name of the user.
     */
    private String name;

    /**
     * Phone number associated with the user.
     */
    private String phone;

    /**
     * Email address associated with the user.
     */
    private String email;

    /**
     * URL or path to the user's avatar image.
     */
    private String avatar;

    /**
     * Role assigned to the user (e.g., ADMIN, USER).
     */
    private String role;

    /**
     * Retrieves the user's unique identifier.
     *
     * @return The user's ID.
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id The user's ID.
     */
    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the user's username.
     *
     * @return The username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The username to set.
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user's password.
     *
     * @return The password.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The password to set.
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the user's nickname or display name.
     *
     * @return The nickname or display name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the user's nickname or display name.
     *
     * @param name The nickname or display name to set.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the user's phone number.
     *
     * @return The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the user's phone number.
     *
     * @param phone The phone number to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the URL or path to the user's avatar.
     *
     * @return The avatar URL or path.
     */
    @Override
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the URL or path to the user's avatar.
     *
     * @param avatar The avatar URL or path to set.
     */
    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Retrieves the user's role.
     *
     * @return The role.
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The role to set.
     */
    @Override
    public void setRole(String role) {
        this.role = role;
    }
}
