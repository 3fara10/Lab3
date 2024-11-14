package org.example.domain.factory;

import org.example.domain.Appointment;
import org.example.domain.Entity;
import org.example.domain.Patient;
import org.example.validators.IValidator;
import org.example.validators.IValidatorFactory;
import org.example.validators.ValidatorFactory;

import java.util.Date;

public class AppointmentFactory implements IEntityFactory<Appointment> {
    private static final IValidatorFactory<Entity> validatorFactory = new ValidatorFactory<>();
    private final IEntityFactory<Patient> patientFactory = EntityFactory.getFactory(Patient.class);

    @Override
    public String toString(Appointment entity) {
        return entity.getId()+";"+patientFactory.toString(entity.getPatient())+";"+org.example.utils.ReadingTypeUtils.writingDate(entity.getDate())+";"+entity.getPurposeAppointment();
    }

    @Override
    public Appointment fromString(String string) {
        String[] tokens = org.example.utils.ReadingTypeUtils.readingStringArray(string);
        int id=org.example.utils.ReadingTypeUtils.readingInt(tokens[0]);
        Patient patient=patientFactory.fromString(tokens[1]+";"+tokens[2]+";"+tokens[3]+";"+tokens[4]);
        Date date=org.example.utils.ReadingTypeUtils.readingDate(tokens[5]);
        String purposeAppointment=org.example.utils.ReadingTypeUtils.readingString(tokens[6]);

        Appointment appointment = new Appointment(id,patient,date,purposeAppointment);
        IValidator<Appointment> appointmentValidator = validatorFactory.getValidator(Appointment.class);
        appointmentValidator.validate(appointment);

        return appointment;
    }
}
