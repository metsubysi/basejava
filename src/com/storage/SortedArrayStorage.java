package com.storage;

import com.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    public void differSave(Resume r) {

        int insertIndex = searchOfIndex(storage, r.getUuid(), size);
        Resume[] newStorage = new Resume[storage.length];
        System.arraycopy(storage, 0, newStorage, 0, insertIndex);
        newStorage[insertIndex] = r;
        System.arraycopy(storage, insertIndex, newStorage, insertIndex + 1, size - insertIndex);
        size++;
        storage =  newStorage;
    }

    @Override
    public void differDelete(int index) {
        // Находим индекс элемента для удаления
        //int removeIndex = searchOfIndex(array, uuid, size);

        // Создаем новый массив с уменьшенным размером
        Resume[] newArray = new Resume[size - 1];

        // Копируем элементы до удаления
        System.arraycopy(storage, 0, newArray, 0, index);

        // Копируем элементы после удаления
        System.arraycopy(storage, index + 1, newArray, index, size - index - 1);

        storage = newArray;
    }
    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);

        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    private static int searchOfIndex(Resume[] storage, String uuid, int size) {
        int left = 0;
        int right = size;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (storage[mid].getUuid().compareTo(uuid) < 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left+"fff");
        return left;
    }
}