package org.example.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a Patient entity with name, forename, and age.
 * Each person has a unique id inherited from Entity superclass.
 */
@JsonTypeName("patient")
@XmlRootElement(name = "patient") // This makes Patient the root element in XML
@XmlType(propOrder = {"id", "name", "forename", "age"}) // Optional: To specify the order of fields in XML
public class Patient extends Entity implements Serializable {

    private String name;
    private String forename;
    private int age;

    @Serial
    private static final long serialVersionUID = 1L;

    public Patient() {}

    /** The constructor of the Patient class. */
    public Patient(String name, String forename, int age) {
        super(); // Initialize the unique id from the Entity class
        this.name = name;
        this.forename = forename;
        this.age = age;
    }

    public Patient(int id, String name, String forename, int age) {
        this.id = id; // Initialize the unique id from the Entity class
        this.name = name;
        this.forename = forename;
        this.age = age;
    }

    // The getters and setters for XML serialization

    @XmlAttribute(name = "id") // Marks the 'id' field as an XML attribute
    @Override
    public int getId() {
        return super.getId();
    }

    @XmlElement(name = "name") // This marks the 'name' field as an XML element
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "forename") // This marks the 'forename' field as an XML element
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    @XmlElement(name = "age") // This marks the 'age' field as an XML element
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() + ", Name: " + this.name + ", Forename: " + this.forename + ", Age: " + this.age;
    }
}
