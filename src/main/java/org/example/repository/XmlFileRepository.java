package org.example.repository;

import org.example.domain.Appointment;
import org.example.domain.Entity;
import org.example.domain.Patient;
import org.example.exceptions.RepositoryException;
import org.example.repository.AbstractFileRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import java.io.*;
import java.util.List;

public class XmlFileRepository<T extends Entity> extends AbstractFileRepository {
    private final JAXBContext jaxbContext;
    private final Class<T> entityClasss;

    public XmlFileRepository(String fileName, Class<T> entityClass) {
        super(fileName);
        this.entityClasss = entityClass;
        try {
            this.jaxbContext = JAXBContext.newInstance(this.entityClasss);
        } catch (JAXBException e) {
            throw new RuntimeException("Error initializing XML repository", e);
        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            this.entities = (List<T>) unmarshaller.unmarshal(br);

        } catch (JAXBException | IOException e) {
            throw new RepositoryException("Error loading XML file", e);
        }
    }

    @Override
    protected void saveFile() throws RepositoryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this.entities, bw);

        } catch (JAXBException | IOException e) {
            throw new RepositoryException("Error saving XML file"+e.getMessage());
        }
    }
}
