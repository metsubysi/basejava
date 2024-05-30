package com.storage;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<SearchKey> implements Storage {

    private static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName)
            .thenComparing(Resume::getUuid);

    public void delete(String uuid) {
        SearchKey index = getExistedSearchKey(uuid);
        doDelete(index);
    }

    public void update(Resume r) {
        SearchKey searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    public Resume get(String uuid) {
        SearchKey index = getExistedSearchKey(uuid);
        return doGet(index);
    }

    public void save(Resume resume) {
        SearchKey searchKey = getNotExistedSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public SearchKey getNotExistingSearchKey(String uuid) {
        SearchKey index = getSearchKey(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    public List<Resume> getAllSorted() {
        List<Resume> allResumes = getAll();
        allResumes.sort(RESUME_COMPARATOR);
        return allResumes;
    }

    private SearchKey getExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            System.out.println("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SearchKey getNotExistedSearchKey(String uuid) {
        SearchKey searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            System.out.println("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(SearchKey searchKey);
    abstract void doDelete(SearchKey index);
    abstract void doUpdate(Resume r,  SearchKey searchKey);
    abstract void doSave(Resume r, SearchKey searchKey);
    abstract Resume doGet(SearchKey searchKey);
    abstract List<Resume> getAll();
    abstract SearchKey getSearchKey(String uuid);
}
