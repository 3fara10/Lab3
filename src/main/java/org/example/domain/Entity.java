package org.example.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.domain.factory.EntityDeserializer;

import java.io.*;
import java.util.Scanner;

/**
 * Represents an entity with a unique identifier.
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
@JsonTypeName("entity")
//@JacksonXmlRootElement(localName = "Entities")
public abstract class Entity implements Serializable{
   // @JacksonXmlElementWrapper(useWrapping = false)
    protected int id;
    private static final IdGenerator idGenerator=IdGenerator.getInstance("C:\\Users\\anton\\IdeaProjects\\Lab3\\src\\main\\java\\org\\example\\entityId.txt");

    @Serial
    private static final long serialVersionUID = 1L;

    /**The constructor of the Entity class.*/
    public Entity(){
        this.id=idGenerator.generateID();
    }

    /**It returns the id of the Entity*/
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
