package com.array;

import com.resume.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    protected final int STORAGE_LIMIT = 10000;
    protected  final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        int i = getIndex(r.toString());
        if (size >= STORAGE_LIMIT) {
            System.out.println("The storage can't contain up to " + STORAGE_LIMIT + " rows");
        } else if (i > -1) {
            System.out.println("This storage contain this resume");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int i = getIndex(uuid);
        if (isExisting(i)) {
            return storage[getIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        int i = getIndex(uuid);
        if (isExisting(i)) {
            size--;
            storage[getIndex(uuid)] = storage[size];
            storage[size] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int i = getIndex(resume.toString());
        String uuid = resume.toString();
        if (isExisting(i)) {
            storage[i] = resume;
        }
    }

    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    boolean isExisting(int i) {
        if (i > -1) {
            return true;
        }
        System.out.println("This resume is not present in the storage");
        return false;
    }
}
