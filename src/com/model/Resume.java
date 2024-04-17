package com.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private String uuid;
    private String fullName = "";


    public Resume() {
        this(UUID.randomUUID().toString());
    }

    public Resume(String fullName){
        this.fullName = fullName;
        this.uuid = "";
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return fullName.equals(resume.fullName);
    }
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public int compareTo(Resume r) {
        return fullName.compareTo(r.fullName);
    }
}
