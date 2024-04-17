package com.storage;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.model.Resume;

import java.util.*;

public abstract class AbstractStorage implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public void delete(String uuid) {
        Object index = getExistingSearchKey(uuid);
        doDelete(index);
    }

    public void update(Resume r) {
        String uuid = r.getFullName();
        Object index = getExistingSearchKey(uuid);
        doUpdate(r, index);
    }

    public Resume get(String fullName) {
        Object index = getExistingSearchKey(fullName);
        return doGet(index);
    }

    public void save(Resume r) {
        String uuid = r.getFullName();
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

    public Object getExistingSearchKey(String fullNme) {
        Object index = getSearchKey(fullNme);
        if (!isExisting(index)) {
            throw new NotExistStorageException(fullNme);
        }
        return index;
    }

    public List<Resume> getAllSorted() {
        List<Resume> allResumes = getAll();
        allResumes.sort(RESUME_COMPARATOR);
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
