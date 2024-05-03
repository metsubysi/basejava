package com.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private String uuid;
    private String fullName = "";
    private Map<ContactsType, String> contacts = new HashMap<>();
    private Map<SectionsType, AbstractSections> sections = new HashMap<>();

    public enum ContactsType {
        EMAIL,
        PHONE,
        SKYPE,
        SOCIAL_MEDIA
    }

    public enum SectionsType {
        OBJECTIVE,
        PERSONAL,
        ACHIEVEMENT,
        QUALIFICATIONS,
        EXPERIENCE,
        EDUCATION
    }

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

    public void addContact(ContactsType type, String value) {
        contacts.put(type, value);
    }

    public String getContact(ContactsType type) {
        return contacts.get(type);
    }

    public void addSection(SectionsType type, AbstractSections section) {
        sections.put(type, section);
    }

    public AbstractSections getSection(SectionsType type) {
        return sections.get(type);
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
