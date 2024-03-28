package com.storage;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.model.Resume;

public abstract class AbstractStorage implements Storage {
    abstract void delete_(Object index);
    abstract void update_(Resume r, Object index);
    abstract Resume get_(Object index);
    abstract void save_(Resume r);
    abstract Object getIndex(String uuid);
    abstract boolean isObtain(Object index);
    public void delete(String uuid)
    {
        Object index = getIndex(uuid);
        if (!isObtain(index)) {
            throw new NotExistStorageException(uuid);
        }
        delete_(index);
    }

    public void update(Resume r)
    {   String uuid = r.getUuid();
        Object index = getIndex(uuid);
        if (!isObtain(index)) {
            throw new NotExistStorageException(uuid);
        }
        update_(r, index);
    }

    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        if (!isObtain(index)) {
            throw new NotExistStorageException(uuid);
        }
        return get_(index);
    }

    public void save(Resume r)
    {   String uuid = r.getUuid();
        Object index = getIndex(uuid);
        if (isObtain(index)) {
            throw new ExistStorageException(uuid);
        }
        save_(r);
    }
}
