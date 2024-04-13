package test;

import com.model.Resume;
import com.storage.MapStorage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MapStorageTest {

    private MapStorage storage;

    private final Resume r1 = new Resume("uuid1");
    private final Resume r2 = new Resume("uuid2");

    @Before
    public void setUp() {
        storage = new MapStorage();
    }

    @Test
    public void testSaveAndGet() {
        storage.save(r1);
        storage.save(r2);

        assertEquals(r1, storage.get("uuid1"));
        assertEquals(r2, storage.get("uuid2"));
    }

    @Test
    public void testUpdate() {
        storage.save(r1);
        Resume updatedResume = new Resume("uuid1");
        storage.update(updatedResume);

        assertEquals(updatedResume, storage.get("uuid1"));
    }

    @Test
    public void testDelete() {
        storage.save(r1);
        storage.delete("uuid1");
        assertEquals(0, storage.size());
    }

    @Test
    public void testSize() {
        storage.save(r1);
        storage.save(r2);

        assertEquals(2, storage.size());
    }

    // Другие тесты для методов getAll, clear и других функций

}
