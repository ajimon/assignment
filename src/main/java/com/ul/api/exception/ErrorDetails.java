package com.ul.api.exception;

import java.util.Date;
/**
 * To store the error details.
 *
 */
public class ErrorDetails {
    /**
     *  To store Date.
     */
    private final Date timestamp;

    /**
     * To store  message.
     */
    private final String message;

    /**
     * To store error details.
     */
    private final String details;

    /**
     * @param ptimestamp
     * @param pmessage
     * @param pdetails
     */
    public ErrorDetails(final Date ptimestamp, final String pmessage,
            final String pdetails) {
        this.timestamp = new Date(ptimestamp.getTime());
        this.message = pmessage;
        this.details = pdetails;
    }

    /**
     * @return {@link Date}
     */
    public Date getTimestamp() {
        return (Date) timestamp.clone();
    }

    /**
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return {@link String}
     */
    public String getDetails() {
        return details;
    }
}
