package com.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    // Unique identifier
    private String uuid;
    private String fullName = "";
    private Map<ContactType, String> contacts = new HashMap<>();
    private Map<SectionType, AbstractSection> sections = new HashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }
    public String getUuid() {
        return this.uuid;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void addContact(ContactType type, String value) {
        contacts.put(type, value);
    }

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public void addSection(SectionType type, AbstractSection section) {
        sections.put(type, section);
    }

    public AbstractSection getSection(SectionType type) {
        return sections.get(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid);
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
    public int compareTo(Resume o) {
        int cmp = fullName.compareTo(o.fullName);
        return cmp != 0 ? cmp : uuid.compareTo(o.uuid);
    }
}
