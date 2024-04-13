package test;

import com.exception.ExistStorageException;
import com.exception.NotExistStorageException;
import com.exception.StorageException;
import com.model.Resume;
import com.storage.AbstractArrayStorage;
import com.storage.Storage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final String u1 = "uuid1";
    private final String u2 = "uuid2";
    private final String u3 = "uuid3";
    private final String u4 = "uuid4";
    private final String d = "dummy";
    private final Resume r1 = new Resume(u1);
    private final Resume r2 = new Resume(u2);
    private final Resume r3 = new Resume(u3);
    private final Resume r4 = new Resume(u4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(u1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(u1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get(d);
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(r1, list.get(0));
        assertEquals(r2, list.get(1));
        assertEquals(r3, list.get(2));
    }

    @Test
    public void save() throws Exception {
        assertSize(3);
        storage.save(r4);
        assertSize(4);
        storage.get(u4);
        assertGet(r4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(u1);
        assertSize(2);
        storage.get(u1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(d);
    }

    @Test
    public void get() throws Exception {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(d);
    }

    private void assertGet(Resume expectedResume) {
        Resume actualResume = storage.get(expectedResume.getUuid());
        assertEquals(expectedResume, actualResume);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }



}
