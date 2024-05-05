package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private String title;
    private String website;
    private List<Period> periods;

    public Organization() {
        this.periods = new ArrayList<>();
    }

    public Organization(String title, String website, List<Period> periods) {
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

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
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
