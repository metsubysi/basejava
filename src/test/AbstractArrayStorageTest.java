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
    private final String UUID1 = "uuid1";
    private final String UUID2 = "uuid2";
    private final String UUID3 = "uuid3";
    private final String UUID4 = "uuid4";
    private final String DUMMY = "dummy";
    private Resume resume1;
    private Resume resume2;
    private Resume resume3;
    private Resume resume4;


    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }
    @Before
    public void setUp() throws Exception {
        storage.clear();
        ResumeTestData rts = new ResumeTestData();
        resume1 = rts.createResume(UUID1, "Григорий Кислин");
        resume2 = rts.createResume(UUID2, "Смирнов Алексей");
        resume3 = rts.createResume(UUID3, "Козлов Дмитрий");
        resume4 = rts.createResume(UUID4, "Попова Екатерина");
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
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
        Resume newResume = new Resume("Григорий Кислин");
        storage.update(newResume);
        assertEquals(newResume, storage.get("Григорий Кислин"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get(DUMMY);
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(resume1, list.get(0));
        assertEquals(resume3, list.get(1));
        assertEquals(resume2, list.get(2));
    }

    @Test
    public void save() throws Exception {
        assertSize(3);
        storage.save(resume4);
        assertSize(4);
        storage.get("Попова Екатерина");
        assertGet(resume4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(resume1);
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
        storage.delete(UUID1);
        assertSize(2);
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(DUMMY);
    }

    @Test
    public void get() throws Exception {
        assertGet(resume1);
        assertGet(resume2);
        assertGet(resume3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY);
    }

    private void assertGet(Resume expectedResume) {
        Resume actualResume = storage.get(expectedResume.getFullName());
        assertEquals(expectedResume, actualResume);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }



}
