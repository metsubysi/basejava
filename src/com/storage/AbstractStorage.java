package com.storage;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    public void delete(String uuid) {
        Object index = getExistingSearchKey(uuid);
        doDelete(index);
    }

    public void update(Resume r) {
        String uuid = r.getUuid();
        Object index = getExistingSearchKey(uuid);
        doUpdate(r, index);
    }

    public Resume get(String uuid) {
        Object index = getExistingSearchKey(uuid);
        return doGet(index);
    }

    public void save(Resume r) {
        String uuid = r.getUuid();
        Object Key = getNotExistingSearchKey(uuid);
        doSave(r);
    }

    public Object getNotExistingSearchKey(String uuid) {
        Object index = getSearchKey(uuid);
        if (isExisting(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    public Object getExistingSearchKey(String uuid) {
        Object index = getSearchKey(uuid);
        if (!isExisting(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    public List<Resume> getAllSorted() {
        List<Resume> allResumes = getAll();
        allResumes.sort(Comparator.comparing(Resume::getUuid).thenComparing(Resume::getUuid));
        return allResumes;
    }

    abstract void doDelete(Object index);
    abstract void doUpdate(Resume r, Object index);
    abstract void doSave(Resume r);
    abstract Resume doGet(Object index);
    abstract List<Resume> getAll();
    abstract Object getSearchKey(String uuid);
    abstract boolean isExisting(Object index);
}
