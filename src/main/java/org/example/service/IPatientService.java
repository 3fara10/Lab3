package org.example.service;

import org.example.domain.Patient;
import org.example.exceptions.*;

import java.util.List;

public interface IPatientService {
    /**
     * Adds a new patient after validating the input data.
     *
     * @param name the name of the patient
     * @param forename the forename of the patient
     * @param age the age of the patient
     * @throws EntityException if the patient data is invalid
     * @throws RepositoryException if a patient with the same ID already exists
     */
    void addPatient(String name, String forename, int age) throws RepositoryException, EntityException;


    /**
     * Retrieves a patient by their ID.
     * @param id the unique identifier of the patient
     * @return the Patient object with the specified ID
     * @throws RepositoryException if no patient with the given ID exists
     */
    Patient getPatient(int id) throws RepositoryException;


    /**
     * Updates an existing patient's information.
     * @param id the unique identifier of the patient to update
     * @param newName the new name of the patient
     * @param newForename the new forename of the patient
     * @param newAge the new age of the patient
     * @throws EntityException if the updated patient data is invalid
     * @throws RepositoryException if the patient with the specified ID does not exist
     */
    void updatePatient(int id, String newName, String newForename, int newAge) throws RepositoryException, EntityException;


    /**
     * Deletes a patient by their ID.
     * @param id the unique identifier of the patient to delete
     * @throws RepositoryException if the patient with the specified ID does not exist
     */
    void deletePatient(int id) throws RepositoryException;


    /**
     * Retrieves all patients from the repository.
     *
     * @return a list of all Patient objects
     */
    List<Patient> getAllPatients();
}