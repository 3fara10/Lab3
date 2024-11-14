package org.example.domain;

import org.example.exceptions.ValidationException;
import org.example.validators.IValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private Patient patient;
    private Date date;
    private String purpose;
    private Appointment appointment;

    @BeforeEach
    void setUp() {
        // Setup common data for the tests
        patient = new Patient(1, "John", "Doe", 30);
        date = new Date(); // current date
        purpose = "Routine check-up";

        // Initialize Appointment object
        appointment = new Appointment(patient, date, purpose);
    }

    @Test
    void testAppointmentConstructor() {
        // Test the constructor with the arguments
        assertNotNull(appointment);
        assertEquals(patient, appointment.getPatient());
        assertEquals(date, appointment.getDate());
        assertEquals(purpose, appointment.getPurposeAppointment());
    }

    @Test
    void testAppointmentDefaultConstructor() {
        // Test the default constructor
        Appointment defaultAppointment = new Appointment();
        assertNotNull(defaultAppointment);
        assertNull(defaultAppointment.getPatient()); // Default should be null
        assertNull(defaultAppointment.getDate()); // Default should be null
        assertNull(defaultAppointment.getPurposeAppointment()); // Default should be null
    }

    @Test
    void testSettersAndGetters() {
        // Test setter and getter methods
        Appointment app = new Appointment();

        app.setPatient(patient);
        app.setDate(date);
        app.setPurposeAppointment(purpose);

        assertEquals(patient, app.getPatient());
        assertEquals(date, app.getDate());
        assertEquals(purpose, app.getPurposeAppointment());
    }

    @Test
    void testToString() {
        // Test the toString() method
        String expectedString = "Entity{id=0}, Patient: Patient{id=0, name='John', forename='Doe', age=30}, Date: " + date + ", The purpose of the appointment: Routine check-up";
        assertTrue(appointment.toString().contains("Patient: " + patient.toString()));
        assertTrue(appointment.toString().contains("Date: " + date.toString()));
        assertTrue(appointment.toString().contains("The purpose of the appointment: Routine check-up"));
    }

    @Test
    void testAppointmentWithId() {
        // Test constructor with id
        Appointment appointmentWithId = new Appointment(1, patient, date, purpose);
        assertEquals(1, appointmentWithId.getId());
        assertEquals(patient, appointmentWithId.getPatient());
        assertEquals(date, appointmentWithId.getDate());
        assertEquals(purpose, appointmentWithId.getPurposeAppointment());
    }

    @Test
    void testSetPatientNull() {
        // Test setting patient to null
        appointment.setPatient(null);
        assertNull(appointment.getPatient());
    }

    @Test
    void testSetDateNull() {
        // Test setting date to null
        appointment.setDate(null);
        assertNull(appointment.getDate());
    }

    @Test
    void testSetPurposeNull() {
        // Test setting purpose to null
        appointment.setPurposeAppointment(null);
        assertNull(appointment.getPurposeAppointment());
    }

    @Test
    void testNullPatientConstructor() {
        // Test constructor with null patient
        Appointment appointmentWithNullPatient = new Appointment(null, date, purpose);
        assertNull(appointmentWithNullPatient.getPatient());
    }

    @Test
    void testNullPurposeConstructor() {
        // Test constructor with null purpose
        Appointment appointmentWithNullPurpose = new Appointment(patient, date, null);
        assertNull(appointmentWithNullPurpose.getPurposeAppointment());
    }

    @Test
    void testNullDateConstructor() {
        // Test constructor with null date
        Appointment appointmentWithNullDate = new Appointment(patient, null, purpose);
        assertNull(appointmentWithNullDate.getDate());
    }

    @Test
    void testValidationException() {
        // Assuming that you have a validator that should throw an exception if validation fails.
        IValidator validator = entity -> {
            if (entity instanceof Appointment) {
                Appointment app = (Appointment) entity;
                if (app.getPatient() == null || app.getDate() == null || app.getPurposeAppointment() == null) {
                    throw new ValidationException("Appointment is invalid");
                }
            }
        };

        Appointment invalidAppointment = new Appointment(null, null, null);
        assertThrows(ValidationException.class, () -> validator.validate(invalidAppointment));
    }

}
