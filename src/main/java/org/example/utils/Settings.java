package org.example.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Settings {
    private String repoType;
    private String fileName;

    // Constructor now takes no arguments
    public Settings() {
    }

    // Getter for repoType
    public String getRepoType() {
        return this.repoType;
    }

    // Getter for fileName
    public String getFileName() {
        return this.fileName;
    }

    // Loads settings specific to the entity class
    public static Settings getInstance(Class<?> entityClass) {
        // Create new Settings instance each time we fetch it for a specific entity class
        Settings instance = new Settings();

        // Read settings from the file
        Properties settings = new Properties();
        try {
            settings.load(new FileReader("C:\\Users\\anton\\IdeaProjects\\Lab3\\src\\main\\java\\org\\example\\settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Error reading settings file", e);
        }

        // Set repo type and file name specific to the entity class
        instance.repoType = settings.getProperty("repo.type");
        instance.fileName = settings.getProperty("file." + entityClass.getSimpleName().toLowerCase());  // Dynamically use entity class name for file setting

        return instance;
    }
}
