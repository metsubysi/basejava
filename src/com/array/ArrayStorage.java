package com.array;

import com.resume.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    int STORAGE_LIMIT = 10000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size <10000) {
            storage[size] = r;
            size++;
        } else {
            System.out.println("The storagacan contain up to 10000 rows");
        }
        isExisting(r.toString());
    }

    public Resume get(String uuid) {
        if (isExisting(uuid)) {
            return storage[getIndex(uuid)];
        }
        return null;
    }

    public void delete(String uuid) {
        if (isExisting(uuid)) {
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
        String uuid = resume.toString();
        if (isExisting(uuid)) {
            storage[getIndex(uuid)] = resume;
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

    boolean isExisting(String uuid) {
        if (getIndex(uuid) > -1) {
            return true;
        }
        System.out.println("The resume " + uuid + " is not present in the storage");
        return false;
    }
}
