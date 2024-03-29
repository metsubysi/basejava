package com.storage;

import com.exception.StorageException;
import com.model.Resume;

import java.util.Arrays;

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
    public void save_(Resume r)
    {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveResume(r);
            size++;
        }
    }

    public void delete_(Object index)
    {
        deleteResume((Integer)index);
        storage[size - 1] = null;
        size--;

    }
    public Resume get_(Object index)
    {
        return storage[((Integer) index).intValue()];
    }

    @Override
    protected void update_(Resume r, Object index)
    {
        storage[(Integer) index] = r;
    }

    @Override
    protected boolean isObtain(Object index) {
        return (Integer) index >= 0;
    }
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }
    protected abstract Integer getIndex_(String uuid);
    abstract void saveResume(Resume r);
    abstract void deleteResume(int i);
}