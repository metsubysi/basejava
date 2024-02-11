package com.storage;

import com.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    void differSave(Resume r) {
        storage[size] = r;
        size++;
    }

    void differDelete(int index)
    {
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
    }
}