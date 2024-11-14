package org.example.service;

import org.example.domain.Appointment;
import org.example.domain.Patient;
import org.example.exceptions.*;
import org.example.repository.IRepository;
import org.example.validators.IValidator;
import org.example.validators.IValidatorFactory;
import org.example.validators.ValidatorFactory;

import java.util.Date;
import java.util.List;

public class AppointmentService implements IAppointmentService {
    private final IRepository<Appointment> appointmentIRepository;
    private final IRepository<Patient> patientIRepository;
    private final IValidatorFactory validatorFactory=new ValidatorFactory();

    public AppointmentService(IRepository<Appointment> appointmentIRepository, IRepository<Patient> patientIRepository) {
        this.appointmentIRepository = appointmentIRepository;
        this.patientIRepository = patientIRepository;
    }


    @Override
    public void addAppointment(int patientId, Date date, String purposeAppointment) throws RepositoryException,EntityException {

        Appointment appointment =new Appointment(this.patientIRepository.getById(patientId),date,purposeAppointment);
        IValidator<Appointment> validator = validatorFactory.getValidator(Appointment.class);
        validator.validate(appointment);
        this.validateOverload(appointment);
        this.appointmentIRepository.add(appointment);
    }

    @Override
    public Appointment getAppointment(int id) throws RepositoryException {
        return this.appointmentIRepository.getById(id);
    }

    @Override
    public void updateAppointment(int idAppointment,int idPatient,Date date,String purposeAppointment) throws RepositoryException,EntityException {

        Appointment appointment = this.getAppointment(idAppointment);
        IValidator<Appointment> validator = validatorFactory.getValidator(Appointment.class);
        appointment.setPatient(this.patientIRepository.getById(idPatient));
        appointment.setDate(date);
        appointment.setPurposeAppointment(purposeAppointment);
        validator.validate(appointment);
        this.appointmentIRepository.update(appointment);
    }

    @Override
    public void deleteAppointment(int id) throws RepositoryException{
        this.appointmentIRepository.delete(this.appointmentIRepository.getById(id));
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return this.appointmentIRepository.getAll();
    }


    /***
     * Verify if an appointment doesn't overlap other one.
     * @param appointment:The new appointment we want to verify it
     */
    private void validateOverload(Appointment appointment) throws RepositoryException {
        //Calculate the milliseconds for the appointment date and appointment date + 60 min
        Date startNew = appointment.getDate();
        Date endNew = new Date(startNew.getTime() + 60 * 60 * 1000);

        for (Appointment existingAppointment : appointmentIRepository.getAll()) {
            //Calculate the milliseconds for each existing appointment date and e.a. date + 60 min
            Date startExisting = existingAppointment.getDate();
            Date endExisting = new Date(startExisting.getTime() + 60 * 60 * 1000);

            if (startNew.before(endExisting) && endNew.after(startExisting)) {
                throw new OverlapException("The new appointment overlaps with an existing appointment.");
            }
        }
    }
}
