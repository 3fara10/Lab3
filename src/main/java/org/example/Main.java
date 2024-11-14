package org.example;
import org.example.domain.Entity;
import org.example.domain.factory.PatientFactory;
import org.example.domain.Appointment;
import org.example.domain.Patient;
import org.example.domain.factory.PatientFactory;
import org.example.exceptions.RepositoryException;
import org.example.repository.*;
import org.example.service.AppointmentService;
import org.example.service.IAppointmentService;
import org.example.service.IPatientService;
import org.example.service.PatientService;
import org.example.ui.IUi;
import org.example.ui.Ui;
import org.example.utils.Settings;
import org.example.validators.IValidatorFactory;
import org.example.validators.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static IRepository getRepository(Class entityClass) {
        Settings s = Settings.getInstance(entityClass);

        if ("memory".equals(s.getRepoType())) {
            return new Repository<>(); // Memory repository
        } else if ("binary".equals(s.getRepoType())) {
            return new BinaryFileRepository<>(s.getFileName()); // Binary repository
        } else if ("text".equals(s.getRepoType())) {
            return new TextFileRepository<>(s.getFileName(), entityClass); // Text repository
        }else if("json".equals(s.getRepoType())) {
            return new JsonFileRepository<>(s.getFileName(), entityClass);
        }else if("xml".equals(s.getRepoType())) {
            return new XmlFileRepository<>(s.getFileName(), entityClass);
        }
        throw new IllegalArgumentException("Fisierul de setari e gresit!");
    }

    public static void main(String[] args){
          IRepository<Patient> patientIRepository = getRepository(Patient.class);
          IRepository<Appointment> appointmentIRepository = getRepository(Appointment.class);
         //PROBLEME MARI MARI MARI LA DESERIALIZARI pe JSON SI XML SA VAD MAINE CE POT FACE!!!!!!!!!!!!!!!!!
        try {
            Patient patient1 = new Patient("John", "Doe", 30);
            Patient patient2 = new Patient("Jane", "Smith", 25);
            Patient patient3 = new Patient("Alice", "Johnson", 40);
            Patient patient4 = new Patient("Alie", "Johnson", 41);
            Patient patient5 = new Patient("Ace", "Johnn", 42);
            SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date appointmentDate1;
            Date appointmentDate2;
            Date appointmentDate3;
            appointmentDate1 = dateFormat.parse("2024-11-01 10:00");
            appointmentDate2 = dateFormat.parse("2024-11-02 11:00");
            appointmentDate3 = dateFormat.parse("2024-11-03 12:00");
            Appointment appointment1 = new Appointment(patient1, appointmentDate1, "General Checkup");
            Appointment appointment2 = new Appointment(patient2, appointmentDate2, "Dental Consultation");
            Appointment appointment3 = new Appointment(patient3, appointmentDate3, "Followup");
            Appointment appointment4 = new Appointment(patient3, appointmentDate3, "Folloup");
            Appointment appointment5 = new Appointment(patient3, appointmentDate3, "Foowp");

            patientIRepository.add(patient1);
            patientIRepository.add(patient2);
            patientIRepository.add(patient3);
            patientIRepository.add(patient4);
            patientIRepository.add(patient5);

            appointmentIRepository.add(appointment1);
            appointmentIRepository.add(appointment2);
            appointmentIRepository.add(appointment3);
            appointmentIRepository.add(appointment4);
            appointmentIRepository.add(appointment5);

        } catch (ParseException | RepositoryException e) {
            System.out.println(e.getMessage());
        }
//

        IPatientService patientService= new PatientService(patientIRepository, appointmentIRepository);
        IAppointmentService appointmentService= new AppointmentService(appointmentIRepository, patientIRepository);
        IUi ui=new Ui(patientService,appointmentService);
        ui.run();
    }
}
