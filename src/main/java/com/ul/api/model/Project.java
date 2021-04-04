package com.ul.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.ul.api.constant.StatusEnum;


/**
 *
 * Project Entity.
 *
 */
@Entity
@Table(name = "project")
public final class Project {

    /**
     * To store  Unique ID.
     */
    private long id;
    /**
     * Name of the project .
     */
    private String name;

    /**
     * Status of the project.
     */
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    /**
     * Project created date .
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime dateCreated;

    /**
     * Archive flag .
     */
    private boolean archived;

    /**
     * Public constructor .
     */
    public Project() {
    }

    /**
     *
     * @param pname
     * @param pstatus
     * @param pdateCreated
     * @param parchived
     */
    public Project(final String pname, final StatusEnum pstatus,
            final LocalDateTime pdateCreated, final boolean parchived) {
        super();
        this.name = pname;
        this.status = pstatus;
        this.dateCreated = pdateCreated;
        this.archived = parchived;
    }

    /**
     * To setting the current date time .
     */
    @PrePersist
    protected void onCreate() {
        dateCreated = LocalDateTime.now();
    }

    /**
     *
     * @return {@link Long}
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    /**
     * @param pid
     */
    public void setId(final long pid) {
        this.id = pid;
    }

    /**
     * @return {@link String}
     */
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * @param pname
     */
    public void setName(final String pname) {
        this.name = pname;
    }

    /**
     * @return {@link StatusEnum}
     */
    @Column(name = "status", nullable = false)
    public StatusEnum getStatus() {
        return status;
    }

    /**
     * @param pstatus
     */
    public void setStatus(final StatusEnum pstatus) {
        this.status = pstatus;
    }

    /**
     * @return {@link LocalDateTime}
     */
    @Column(name = "dateCreated", nullable = false)
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * @param pdateCreated
     */
    public void setDateCreated(final LocalDateTime pdateCreated) {
        this.dateCreated = pdateCreated;
    }

    /**
     * @return {@link Boolean}
     */
    @Column(name = "archived", columnDefinition = "boolean default false")
    public boolean isArchived() {
        return archived;
    }

    /**
     * @param parchived
     */
    public void setArchived(final boolean parchived) {
        this.archived = parchived;
    }
}
