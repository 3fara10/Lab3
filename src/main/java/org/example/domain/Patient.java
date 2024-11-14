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

/**Represents a Patient entity with name,forename and age.
 * Each person has a unique id inherited from Entity superclass.
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
@JsonTypeName("patient")
public class Patient extends Entity implements Serializable {
    private String name;
    private String forename;
    private int age;

    @Serial
    private static final long serialVersionUID = 1L;

    public Patient() {}
    /**The constructor of the Patient class.*/
    public Patient(String name, String forename, int age) {

        //Initialize the unique id from the Entity class
        super();
        this.name = name;
        this.forename = forename;
        this.age = age;
    }

    public Patient(int id, String name, String forename, int age) {
        this.id=id;
        this.name = name;
        this.forename = forename;
        this.age = age;
    }

    //The getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() + ", Name: " + this.name + ", Forename: " + this.forename + ", Age: " + this.age;
    }


}
