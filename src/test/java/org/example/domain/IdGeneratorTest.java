package org.example.domain;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    private static final String TEST_FILE_NAME = "entityId_test.txt";

    @BeforeEach
    void setUp() throws IOException {
        // Reset or create the test file with an initial value of 100
        Files.write(Paths.get(TEST_FILE_NAME), "100".getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    }

    @Test
    void testSingletonInstance() {
        // Ensure only one instance is created
        IdGenerator instance1 = IdGenerator.getInstance(TEST_FILE_NAME);
        IdGenerator instance2 = IdGenerator.getInstance(TEST_FILE_NAME);

        assertSame(instance1, instance2);
    }

    @Test
    void testInitialIdFromFile() {
        // Check that the initial ID is read correctly from the file
        IdGenerator idGenerator = IdGenerator.getInstance(TEST_FILE_NAME);
        assertEquals(101, idGenerator.generateID());
    }

    @Test
    void testIdIncrementsCorrectly() {
        // Check if IDs increment correctly
        IdGenerator idGenerator = IdGenerator.getInstance(TEST_FILE_NAME);
        int firstId = idGenerator.generateID();
        int secondId = idGenerator.generateID();

        assertEquals(firstId + 1, secondId);
    }

    @Test
    void testPersistenceOfLastId() {
        // Generate an ID and check if it is persisted in the file
        IdGenerator idGenerator = IdGenerator.getInstance(TEST_FILE_NAME);
        int generatedId = idGenerator.generateID();

        // Re-initialize IdGenerator to see if it reads the last ID correctly
        IdGenerator newIdGenerator = IdGenerator.getInstance(TEST_FILE_NAME);
        assertEquals(generatedId + 1, newIdGenerator.generateID());
    }

    @Test
    void testFileNotFoundCreatesDefaultId() {
        // Delete the file to simulate it not existing
        try {
            Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
        } catch (IOException e) {
            fail("Failed to delete test file");
        }

        IdGenerator idGenerator = IdGenerator.getInstance(TEST_FILE_NAME);
        assertEquals(102, idGenerator.generateID()); // Since default is 100, the first generated ID should be 101
    }
}
