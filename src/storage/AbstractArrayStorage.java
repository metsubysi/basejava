package storage;

import model.Resume;

import java.util.Arrays;
import Exception.*;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 1000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count = 0;

    public void clear() {
        Arrays.fill(storage, 0, size(), null);
        count = 0;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        if  (count >= STORAGE_LIMIT) {
            throw new StorageException("Storage Overflow", r.getUuid());
        } else {
            insertElement(r, (Integer) searchKey);
            count++;
        }
    }

    @Override
    protected void doDelete(Object index) {
        fillDeletedElement((Integer) index);
        storage[count - 1] = null;
        count--;
    }

    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public void update(Resume r) {
        int index = getSearchKey(r.getUuid());
        if (index == -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    public int size() {
        return count;
    }
    protected abstract Integer getSearchKey(String uuid);
    protected abstract void insertElement(Resume r, int index);
    protected abstract void fillDeletedElement(int index);
}
