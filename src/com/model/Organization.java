package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String website;
    private List<Position> periods;

    public Organization() {
        this.periods = new ArrayList<>();
    }

    public Organization(String title, String website, List<Position> periods) {
        this.title = title;
        this.website = website;
        this.periods = periods;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Position> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Position> periods) {
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(website, that.website) &&
                Objects.equals(periods, that.periods);
    }

    public String toString() {
        return title + "\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, website, periods);
    }
}
