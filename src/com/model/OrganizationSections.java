package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrganizationSections extends AbstractSections{
    private List<Organization> organizations;

    public OrganizationSections() {
        this.organizations = new ArrayList<>();
    }

    public OrganizationSections(List<Organization> organizations) {
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
        OrganizationSections that = (OrganizationSections) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
