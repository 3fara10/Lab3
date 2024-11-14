package org.example.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.factory.EntityDeserializer;
import org.example.exceptions.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    private Patient patient;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        // Setup common data for the tests
        patient = new Patient(1, "John", "Doe", 30);
        appointment = new Appointment(patient, new java.util.Date(), "Routine Checkup");
    }

    @Test
    void testEntityIdGeneration() {
        // Test that the ID is being correctly generated by the IdGenerator
        int patientId = patient.getId();
        int appointmentId = appointment.getId();

        // Verify that the ID is not 0 (since IDs are generated dynamically)
        assertNotEquals(0, patientId);
        assertNotEquals(0, appointmentId);
        assertNotEquals(patientId, appointmentId); // Check that IDs for different entities are unique
    }

    @Test
    void testEntityToString() {
        // Test the toString method of the Entity class
        String patientString = patient.toString();
        String appointmentString = appointment.toString();

        assertTrue(patientString.contains("ID:"));
        assertTrue(appointmentString.contains("ID:"));

        // Check that ID is printed
        assertTrue(patientString.startsWith("ID:"));
        assertTrue(appointmentString.startsWith("ID:"));
    }

//    @Test
//    void testSerializationAndDeserialization() throws Exception {
//        // Test the Jackson serialization/deserialization for Entity (using Appointment or Patient)
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Serialize the Appointment object
//        String json = objectMapper.writeValueAsString(appointment);
//
//        // Ensure that JSON contains the "type" property to distinguish the entity
//        assertTrue(json.contains("\"type\":\"appointment\""));
//
//        // Deserialize the JSON back to an Appointment object
//        Appointment deserializedAppointment = objectMapper.readValue(json, Appointment.class);
//
//        // Check that the deserialized object has the same properties as the original
//        assertEquals(appointment.getId(), deserializedAppointment.getId());
//        assertEquals(appointment.getPatient().getName(), deserializedAppointment.getPatient().getName());
//        assertEquals(appointment.getPurposeAppointment(), deserializedAppointment.getPurposeAppointment());
//    }

//    @Test
//    void testJsonTypeInfo() {
//        // Test the @JsonTypeInfo and @JsonSubTypes annotations to check if polymorphism works correctly
//        String patientJson = "{\"type\":\"patient\",\"id\":1,\"name\":\"John\",\"forename\":\"Doe\",\"age\":30}";
//        String appointmentJson = "{\"type\":\"appointment\",\"id\":2,\"patient\":{\"id\":1,\"name\":\"John\",\"forename\":\"Doe\",\"age\":30},\"date\":\"2024-11-13T10:00:00.000Z\",\"purposeAppointment\":\"Routine checkup\"}";
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Patient deserializedPatient = objectMapper.readValue(patientJson, Patient.class);
//            Appointment deserializedAppointment = objectMapper.readValue(appointmentJson, Appointment.class);
//
//            // Check if the deserialized objects are of the correct type
//            assertTrue(deserializedPatient instanceof Patient);
//            assertTrue(deserializedAppointment instanceof Appointment);
//        } catch (IOException e) {
//            fail("Deserialization failed: " + e.getMessage());
//        }
//    }

    @Test
    void testIdGeneratorSingleton() {
        // Check if the IdGenerator is a singleton and it generates unique IDs across different entities
        IdGenerator idGenerator = IdGenerator.getInstance("C:\\path\\to\\entityId.txt");
        int firstGeneratedId = idGenerator.generateID();
        int secondGeneratedId = idGenerator.generateID();

        // Ensure that the generated IDs are unique
        assertNotEquals(firstGeneratedId, secondGeneratedId);
    }

    @Test
    void testEntityConstructor() {
        // Test that the entity constructor initializes the ID correctly
        Entity entity = new Patient(1, "John", "Doe", 30);

        assertNotNull(entity.getId()); // ID should not be null
        assertTrue(entity.getId() > 0); // ID should be greater than zero
    }

}