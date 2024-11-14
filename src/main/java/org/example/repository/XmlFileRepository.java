package org.example.repository;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.example.domain.Entity;
import org.example.exceptions.RepositoryException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XmlFileRepository<T extends Entity> extends AbstractFileRepository {
    private Class<T> entityClass; // Tipul entității
    private JAXBContext jaxbContext;

    public XmlFileRepository(String fileName, Class<T> entityClass) {
        super(fileName);
        this.entityClass = entityClass;
        try {
            this.jaxbContext = JAXBContext.newInstance(entityClass);
            //loadFile();
        } catch (JAXBException e) {
           throw new RuntimeException("Eroare la inițializarea repository-ului XML", e);
        }
    }

    @Override
    protected void loadFile() throws RepositoryException {
        File file = new File(this.fileName);

        if (file.length() == 0) {
            this.entities = new ArrayList<T>();
            return;
        }
        try (FileReader fileReader = new FileReader(file);
             BufferedReader br = new BufferedReader(fileReader)) {


            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            this.entities = (List<T>) unmarshaller.unmarshal(br);

        } catch (JAXBException | IOException e) {
            throw new RepositoryException("Eroare la citirea fișierului XML", e);
        }
    }

    @Override
    protected void saveFile() throws RepositoryException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName))) {

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(this.entities, bw);

        } catch (JAXBException | IOException e) {
            throw new RepositoryException("Eroare la salvarea fișierului XML", e);
        }
    }
}
