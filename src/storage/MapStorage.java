package storage;

import model.Resume;

import java.util.*;
import java.util.stream.Collectors;

import Exception.NotExistStorageException;
import Exception.ExistStorageException;

public class MapStorage extends AbstractStorage{

    private Map<String, Resume> map = new LinkedHashMap<>();
    @Override
    protected Object getSearchKey(String uuid) {
        if (map.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String) searchKey, r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey.toString());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String) searchKey);
    }

    @Override
    protected void insertElement(Resume r, int index) {

    }

    @Override
    protected void fillDeletedElement(int index) {

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list;
        list = map.values()
                .stream()
                .sorted(Comparator.comparing(Resume::getUuid))
                .toList();
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }
}
