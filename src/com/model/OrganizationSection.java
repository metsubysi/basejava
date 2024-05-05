package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSection extends AbstractSection{
    private List<Organization> organizations;

    public OrganizationSection() {
        this.organizations = new ArrayList<>();
    }

    public OrganizationSection(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Organization org : organizations) {
            for (Period period : org.getPeriods()) {
                sb.append(org.toString()).append("");
                sb.append(period.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
