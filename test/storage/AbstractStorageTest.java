package storage;

import model.Resume;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;
import Exception.NotExistStorageException;
import Exception.ExistStorageException;
import Exception.StorageException;

public abstract class AbstractStorageTest {

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    public Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_5 = "uuid5";
    private static final String UUID_6 = "uuid6";
    private static final Resume R1;
    private static final Resume R2;
    private static final Resume R3;
    private static final Resume R4;
    private static final Resume R5;
    private static final Resume R6;

    static {
            R1 = new Resume(UUID_1);
            R2 = new Resume(UUID_2);
            R3 = new Resume(UUID_3);
            R4 = new Resume(UUID_4);
            R5 = new Resume(UUID_5);
            R6 = new Resume(UUID_6);
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
        storage.save(R4);
        storage.save(R5);
    }

    @org.junit.Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @org.junit.Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @org.junit.Test
    public void getAll() throws Exception {
        Resume [] array = storage.getAll();
        assertEquals(5, array.length);
        assertEquals(R1, array[0]);
        assertEquals(R2, array[1]);
        assertEquals(R3, array[2]);
        assertEquals(R4, array[3]);
        assertEquals(R5, array[4]);
    }

    @org.junit.Test
    public void get() throws Exception {
        assertGet(R1);
        assertGet(R2);
        assertGet(R3);
        assertGet(R4);
        assertGet(R5);
    }

    @org.junit.Test
    public void save() throws Exception {
        storage.save(R6);
        assertSize(6);
        assertGet(R6);
    }

    @org.junit.Test (expected = NotExistStorageException.class)
    public void delete() throws Exception{
        storage.delete(UUID_4);
        assertSize(4);
        storage.get(UUID_4);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception{
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.delete("DUMMY_UUID_THAT_DOES_NOT_EXIST");
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception{
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.update(storage.get("DUMMY_UUID_THAT_DOES_NOT_EXIST"));
    }

    @org.junit.Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception{
        // Пытаемся удалить несуществующий UUID (который выдаст индекс -1)
        storage.save(new Resume(UUID_2));
    }

    @org.junit.Test
    public void size() {
        assertSize(5);
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @org.junit.Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 5; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}