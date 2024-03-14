package com.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.model.Resume;
import org.junit.Ignore;
import org.junit.Test;

public class AbstractArrayStorageDiffblueTest {
    /**
     * Method under test: {@link AbstractArrayStorage#save(Resume)}
     */
    @Test
    public void testSave() {
        // Arrange
        ArrayStorage arrayStorage = new ArrayStorage();
        Resume r = new Resume();

        // Act
        arrayStorage.save(r);

        // Assert
        assertEquals(1, arrayStorage.size());
        assertSame(r, arrayStorage.storage[0]);
    }

    /**
     * Method under test: {@link AbstractArrayStorage#save(Resume)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSave2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.model.Resume.getUuid()" because "r" is null
        //       at com.storage.AbstractArrayStorage.save(AbstractArrayStorage.java:23)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new ArrayStorage()).save(null);
    }
}
