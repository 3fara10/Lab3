package org.example.domain.factory;

import org.example.domain.Entity;
import org.example.domain.Patient;
import org.example.exceptions.EntityException;
import org.example.validators.IValidator;
import org.example.validators.IValidatorFactory;
import org.example.validators.ValidatorFactory;

public class PatientFactory implements IEntityFactory<Patient> {

    private static final IValidatorFactory<Entity> validatorFactory = new ValidatorFactory<>();

    @Override
    public String toString(Patient patient) {
        return patient.getId()+";"+patient.getName()+";"+patient.getForename()+";"+patient.getAge();
    }

    @Override
    public Patient fromString(String string) throws EntityException{
        String[] tokens = org.example.utils.ReadingTypeUtils.readingStringArray(string);
        int id=org.example.utils.ReadingTypeUtils.readingInt(tokens[0]);
        String name=org.example.utils.ReadingTypeUtils.readingString(tokens[1]);
        String forename=org.example.utils.ReadingTypeUtils.readingString(tokens[2]);
        int age=org.example.utils.ReadingTypeUtils.readingInt(tokens[3]);

        Patient patient = new Patient(id, name, forename, age);
        IValidator<Patient> patientValidator = validatorFactory.getValidator(Patient.class);
        patientValidator.validate(patient);

        return patient;
    }
}
