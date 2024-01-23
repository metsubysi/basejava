package com.array;

import com.resume.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
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
        isElementPresent(r.toString());
    }

    public Resume get(String uuid) {
        if (isElementPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        if (isElementPresent(uuid)) {
            for (int i = 0; i < (size - 1); i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = storage[size];
                    storage[size] = null;
                    break;
                }
            }
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
        if (isElementPresent(uuid)) {
            for (int i = 0; i < (size - 1); i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = resume;
                    break;
                }
            }
        }
    }

    boolean isElementPresent(String uuid) {
        for (int i = 0; i < (size - 1); i++) {
            if (storage[i].toString().equals(uuid)) {
                return true;
            }
        }
        System.out.println("The resume " + uuid + " is not present in the storage");
        return false;
    }
}
