package storage;

import model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import Exception.*;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 1000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count = 0;

    public void clear() {
        Arrays.fill(storage, 0, size(), null);
        count = 0;
    }

    @Override
    protected void doUpdate(Resume r, Integer index) {
        storage[index] = r;
    }

    @Override
    protected void doSave(Resume r, Integer index) {
        if  (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", r.getUuid());
        } else {
            insertElement(r, index);
            count++;
        }
    }

    @Override
    protected void doDelete(Integer index) {
        fillDeletedElement(index);
        storage[count - 1] = null;
        count--;
    }

    public Resume doGet(Integer index) {
        return storage[index];
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size()));
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    public int size() {
        return count;
    }
    protected abstract Integer getSearchKey(String uuid);
    protected abstract void insertElement(Resume r, int index);
    protected abstract void fillDeletedElement(int index);
}
