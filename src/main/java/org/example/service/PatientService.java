package org.example.service;

import org.example.domain.Appointment;
import org.example.domain.Patient;
import org.example.exceptions.*;
import org.example.repository.IRepository;
import org.example.validators.IValidator;
import org.example.validators.IValidatorFactory;
import org.example.validators.ValidatorFactory;

import java.util.Iterator;
import java.util.List;

public class PatientService implements IPatientService {

    private final IRepository<Patient> patientIRepository;
    private final IRepository<Appointment> appointmentIRepository;
    private final IValidatorFactory validatorFactory=new ValidatorFactory();

    /**
     * The constructor for the service class
     *
     * @param patientIRepository     the repository to manage Patients
     * @param appointmentIRepository the repository to manage Appointments
     */
    public PatientService(IRepository<Patient> patientIRepository, IRepository<Appointment> appointmentIRepository) {
        this.patientIRepository = patientIRepository;
        this.appointmentIRepository = appointmentIRepository;
    }

    /***
     * Add a patient in the Repository
     * @param age : The age of the patient
     * @param forename: The forename of the patient
     * @param name: The name of the patient
     * @throws ValidationException if the name,forename or age is not good.
     * @throws DuplicateException if the patient already exists.
     */
    @Override
    public void addPatient(String name, String forename, int age) throws ValidationException, RepositoryException {
        IValidator<Patient> validator=validatorFactory.getValidator(Patient.class);
        Patient patient = new Patient(name, forename, age);
        validator.validate(patient);
        this.patientIRepository.add(patient);
    }

    /**
     * Returns the patient with the id.
     *
     * @param id : The id of the patient
     */
    @Override
    public Patient getPatient(int id) throws RepositoryException {
        return patientIRepository.getById(id);
    }

    /**
     * @param id           : The id of the patient.
     * @param newName:     The new name of the patient
     * @param newForename: The new forename of the patient
     * @param newAge:      The new age of the patient
     * @throws ValidationException if the name,forename or age is not good.
     * @throws NotExistException  if the patient doesn't exist.
     */
    @Override
    public void updatePatient(int id, String newName, String newForename, int newAge) throws EntityException, RepositoryException {

        Patient patient = this.patientIRepository.getById(id);
        patient.setName(newName);
        patient.setForename(newForename);
        patient.setAge(newAge);
        IValidator<Patient> validator=validatorFactory.getValidator(Patient.class);
        validator.validate(patient);
        this.patientIRepository.update(patient);

        //To update all its appointments with the new patient
        for (Appointment appointment : appointmentIRepository.getAll()) {
            if (appointment.getPatient().getId() == id) {
                appointment.setPatient(patient);
                appointmentIRepository.update(appointment);
            }
        }

    }

    /**Delete a patient from the repository.
     * @param id : The id of the patient.
     * @throws NotExistException if the patient doesn't exist
     */
    @Override
    public void deletePatient(int id) throws RepositoryException {
        Iterator<Appointment> iterator = appointmentIRepository.getAll().iterator();

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatient().getId() == id) {
                iterator.remove();
            }
        }

        this.patientIRepository.delete(this.patientIRepository.getById(id));
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientIRepository.getAll();
    }


}
