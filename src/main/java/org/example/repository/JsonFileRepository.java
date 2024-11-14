package org.example.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.example.domain.Entity;
import org.example.domain.factory.EntityFactory;
import org.example.domain.factory.IEntityFactory;
import org.example.exceptions.RepositoryException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonFileRepository<T extends Entity> extends AbstractFileRepository<T> {
    private final IEntityFactory<T> factory;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> entityClass;

    public JsonFileRepository(String fileName, Class<T> entityClass) {
        super(fileName);
        this.factory = EntityFactory.getFactory(entityClass);
        this.entityClass = entityClass;
        try {
            loadFile();
        } catch (RepositoryException e) {
            throw new RuntimeException("Failed to initialize repository", e);
        }
    }

    @Override
    protected void saveFile() throws RepositoryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            String jsonContent = objectMapper.writeValueAsString(this.entities);
            bw.write(jsonContent);
        } catch (IOException e) {
            throw new RepositoryException("Error saving JSON file", e);
        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, entityClass);
            this.entities = objectMapper.readValue(br, collectionType);
        } catch (IOException e) {
            throw new RepositoryException("Error loading from JSON file", e);
        }
    }
}
