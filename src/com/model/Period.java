package com.model;

import java.util.Date;
import java.util.Objects;

public class Period {
    private Date startDate;
    private Date endDate;
    private String description;
    private String position;

    public Period() {
        // Default constructor
    }

    public Period(Date startDate, Date endDate, String description, String position) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.position = position;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(endDate, period.endDate) &&
                Objects.equals(description, period.description) &&
                Objects.equals(position, period.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, description, position);
    }
}
