package org.example.domain;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.domain.factory.EntityDeserializer;
import org.example.exceptions.ValidationException;
import org.example.validators.IValidator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
/**Represents an Appointment entity with patient,date and purpose of the Appointment.
 * Each Appointment has a unique id inherited from Entity superclass.
 */

//@JsonTypeInfo(
//        use = JsonTypeInfo.Id.NAME,
//        include = JsonTypeInfo.As.PROPERTY,
//        property = "type"
//)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = Patient.class, name = "patient"),
//        @JsonSubTypes.Type(value = Appointment.class, name = "appointment")
//})
//@JsonDeserialize(using = EntityDeserializer.class)
@JsonTypeName("appointment")
public class Appointment extends Entity implements Serializable {
    private Patient patient;
    private Date date;
    private String purposeAppointment;
    @Serial
    private static final long serialVersionUID = 1L;

    public Appointment() {}
    /**The constructor of the Appointment class.*/
    public Appointment(Patient patient, Date date, String purposeAppointment) {
        //Initialize the unique id from the Entity class
        super();
        this.patient = patient;
        this.date = date;
        this.purposeAppointment = purposeAppointment;
    }

    public Appointment(int id,Patient patient, Date date, String purposeAppointment) {
        //Initialize the unique id from the Entity class
        this.id=id;
        this.patient = patient;
        this.date = date;
        this.purposeAppointment = purposeAppointment;
    }

    //The getters and setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getPurposeAppointment() {
        return purposeAppointment;
    }

    public void setPurposeAppointment(String purposeAppointment) {
        this.purposeAppointment = purposeAppointment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + ", Patient: " + patient + ", Date: " + date + ", The purpose of the appointment: " + purposeAppointment;
    }

}
