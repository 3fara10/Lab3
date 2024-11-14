package org.example.domain;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void testPatientConstructor() {
        // Create a Patient using the constructor
        Patient patient = new Patient("John", "Doe", 30);

        // Validate fields are correctly set
        assertEquals("John", patient.getName());
        assertEquals("Doe", patient.getForename());
        assertEquals(30, patient.getAge());
        assertTrue(patient.getId() > 0);  // ID should be initialized with a unique positive value
    }

    @Test
    void testPatientConstructorWithId() {
        // Create a Patient using the constructor with an ID
        Patient patient = new Patient(123, "Jane", "Smith", 25);

        // Validate fields
        assertEquals(123, patient.getId());
        assertEquals("Jane", patient.getName());
        assertEquals("Smith", patient.getForename());
        assertEquals(25, patient.getAge());
    }

    @Test
    void testSettersAndGetters() {
        Patient patient = new Patient();

        // Use setters
        patient.setName("Alice");
        patient.setForename("Johnson");
        patient.setAge(40);

        // Verify getters
        assertEquals("Alice", patient.getName());
        assertEquals("Johnson", patient.getForename());
        assertEquals(40, patient.getAge());
    }

    @Test
    void testToString() {
        Patient patient = new Patient(200, "Eve", "Williams", 45);

        // Verify the toString output contains expected details
        String expectedOutput = "ID: 200, Name: Eve, Forename: Williams, Age: 45";
        assertEquals(expectedOutput, patient.toString());
    }

    //@Test
//    void testSerialization() {
//        // Create a Patient instance
//        Patient patient = new Patient(300, "Bob", "Brown", 55);
//
//        try {
//            // Serialize the patient
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(patient);
//
//            // Deserialize the patient
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            Patient deserializedPatient = (Patient) objectInputStream.readObject();
//
//            // Verify the deserialized object's fields
//            assertEquals(patient.getId(), deserializedPatient.getId());
//            assertEquals(patient.getName(), deserializedPatient.getName());
//            assertEquals(patient.getForename(), deserializedPatient.getForename());
//            assertEquals(patient.getAge(), deserializedPatient.getAge());
//
//        } catch (IOException | ClassNotFoundException e) {
//            fail("Serialization failed with exception: " + e.getMessage());
//        }
  //  }

    @Test
    void testInvalidAge() {
        // Verify if age validation or exception should be implemented
        Patient patient = new Patient("Tom", "White", -5);

        // Assuming a requirement for non-negative age:
        assertTrue(patient.getAge() >= 0, "Age should be non-negative.");
    }
}
