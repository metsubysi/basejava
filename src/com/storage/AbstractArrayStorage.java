package com.storage;

import com.exception.StorageException;
import com.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {

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

    public void doSave(Resume r) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r);
            size++;
        }
    }

    public void doDelete(Object index) {
        deleteResume((Integer)index);
        storage[size - 1] = null;
        size--;
    }

    public Resume doGet(Object index) {
        return storage[((Integer) index)];
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    @Override
    protected boolean isExisting(Object index) {
        return (Integer) index >= 0;
    }

    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected abstract Integer getSearchKey(String uuid);
    abstract void saveResume(Resume r);
    abstract void deleteResume(int i);
}