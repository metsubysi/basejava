package storage;

import Exception.ExistStorageException;
import Exception.NotExistStorageException;
import Exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @org.junit.Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 5; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("Максим Морозов" + i));
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume("Максим Морозов"));
    }
}