package test;

import com.exception.NotExistStorageException;
import com.model.Resume;
import com.storage.MapStorage;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MapStorageTest {
    private MapStorage storage;

    private final String u1 = "uuid1";
    private final String u2 = "uuid2";
    private final String u3 = "uuid3";
    private final String u4 = "uuid4";
    private final Resume r1 = new Resume(u1);
    private final Resume r2 = new Resume(u2);
    private final Resume r3 = new Resume(u3);
    private final Resume r4 = new Resume(u4);

    @Before
    public void setUp() {
        storage = new MapStorage();
    }

    @Test
    public void testClear() {
        storage.save(r1);
        storage.save(r2);
        storage.clear();
        assertEquals(0, storage.getAll().length);
    }

    @Test
    public void testUpdate() {
        storage.save(r1);
        storage.update(r1);
        assertEquals(r1, storage.get(u1));
    }

    @Test
    public void testSaveAndGet() {
        storage.save(r3);
        assertEquals(r3, storage.get(u3));
    }

    @Test
    public void testDelete() {
        storage.save(r4);
        storage.delete(u4);
        try {
            storage.get(u4);
            fail("Expected NotExistStorageException to be thrown");
        } catch (NotExistStorageException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testGetAll() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        Resume[] allResumes = storage.getAll();
        assertEquals(3, allResumes.length);
        assertTrue(Arrays.asList(allResumes).contains(r1));
        assertTrue(Arrays.asList(allResumes).contains(r2));
        assertTrue(Arrays.asList(allResumes).contains(r3));
    }
}
