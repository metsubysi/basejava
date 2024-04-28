package test;

import com.model.Resume;
import com.storage.MapStorage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest {

    private MapStorage storage;

    private final Resume resume1 = new Resume("uuid1");
    private final Resume resume2 = new Resume("uuid2");

    @Before
    public void setUp() {
        storage = new MapStorage();
    }

    @Test
    public void testSaveAndGet() {
        storage.save(resume1);
        storage.save(resume2);

        assertEquals(resume1, storage.get("uuid1"));
        assertEquals(resume2, storage.get("uuid2"));
    }

    @Test
    public void testUpdate() {
        storage.save(resume1);
        Resume updatedResume = new Resume("uuid1");
        storage.update(updatedResume);

        assertEquals(updatedResume, storage.get("uuid1"));
    }

    @Test
    public void testDelete() {
        storage.save(resume1);
        storage.delete("uuid1");
        assertEquals(0, storage.size());
    }

    @Test
    public void testSize() {
        storage.save(resume1);
        storage.save(resume2);

        assertEquals(2, storage.size());
    }

    // Другие тесты для методов getAll, clear и других функций

}
