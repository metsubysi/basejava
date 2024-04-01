package com.storage;

import com.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void saveResume(Resume r) {
        int insertIndex = Arrays.binarySearch(storage, 0, size, r);
        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }
        for (int i = size; i > insertIndex; i--) {
            storage[i] = storage[i - 1];
        }
        storage[insertIndex] = r; 
    }

    @Override
    public void deleteResume(int index) {
        for (int i = index; i < size - 1; i++) {
            storage[i] = storage[i + 1];
        }
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}