package com.example.common.enums;

/**
 * Enumeration representing the status of an entity or operation.
 * Used to indicate various stages in a review process.
 */
public enum StatusEnum {

    /**
     * Status indicating the entity is under review.
     */
    CHECKING("Under Review"),

    /**
     * Status indicating the entity has been reviewed and approved.
     */
    CHECK_OK("Reviewed"),

    /**
     * Status indicating the entity has been reviewed and rejected.
     */
    CHECK_NO("Review Failed");

    /**
     * A human-readable description of the status.
     */
    public String status;

    /**
     * Constructor to assign a description to the enum constant.
     *
     * @param status The description of the status.
     */
    StatusEnum(String status) {
        this.status = status;
    }
}
