package com.storage;

import com.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage{

    Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getUuid)
            .thenComparing(Resume::getFullName);

    @Override
    public void saveResume(Resume r) {
        int insertIndex = Arrays.binarySearch(storage, 0, size, r);
        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
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
        int index = Arrays. binarySearch(storage, 0, size, searchKey, resumeComparator);
        return index >= 0 ? index : -1;
    }
}