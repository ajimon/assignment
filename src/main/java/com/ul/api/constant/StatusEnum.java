package com.ul.api.constant;

/**
 * @author Ajimon.Sanku
 * This class is used to store the status
 */
public enum StatusEnum {
    /**
     * In Progress.
     */
    IN_PROGRESS("IN_PROGRESS"),
    /**
     * Finished.
     */
    FINISHED("FINISHED");

    /**
     * Status.
     */
    private final String status;

    /**
     * @param pstatus
     */
    StatusEnum(final String pstatus) {
        this.status = pstatus;
    }

    /**
     * @return {@link String}
     */
    public String status() {
        return status;
    }
}
