package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListSections extends AbstractSections{
    private List<String> strings;

    public ListSections() {
        this.strings = new ArrayList<>();
    }

    public ListSections(List<String> strings) {
        this.strings = strings;
    }

    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "ListSections{" +
                "strings=" + strings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSections that = (ListSections) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }
}
