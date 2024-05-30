package com.storage;

import com.exception.StorageException;
import com.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {

    public static final int STORAGE_LIMIT = 10000;
    int size;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r, index);
            size++;
        }
    }

    @Override
    public void doDelete(Integer index) {
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public Resume doGet(Integer index) {
        return storage[index];
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected abstract Integer getSearchKey(String uuid);
    abstract void saveResume(Resume r, int index);
    abstract void deleteResume(int index);
}