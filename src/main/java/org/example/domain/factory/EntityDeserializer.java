package org.example.domain.factory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.example.domain.Appointment;
import org.example.domain.Entity;
import org.example.domain.Patient;

import java.io.IOException;

public class EntityDeserializer extends JsonDeserializer<Entity> {
    @Override
    public Entity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectNode node = p.getCodec().readTree(p);
        String type = node.get("type").asText();

        if ("patient".equals(type)) {
            return p.getCodec().treeToValue(node, Patient.class); // Deserialize as Patient
        } else if ("appointment".equals(type)) {
            return p.getCodec().treeToValue(node, Appointment.class); // Deserialize as Appointment
        }

        throw new IOException("Unknown type: " + type);
    }
}
