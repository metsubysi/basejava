package com.storage;

import com.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    void saveResume(Resume r) {
        storage[size] = r;
    }

    void deleteResume(int index) {
        storage[index] = storage[size - 1];
    }
}