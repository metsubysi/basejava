package com.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String position;

    public Position() {
        // Default constructor
    }

    public Position(LocalDate startDate, LocalDate endDate, String description, String position) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.position = position;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public String toString() {
        return "C " + startDate +
                " по " + endDate + "\n" + position +
                "\n" + description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position period = (Position) o;
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
