package org.example.ui;

import org.example.domain.Appointment;
import org.example.exceptions.*;
import org.example.service.IAppointmentService;
import org.example.utils.UiUtils;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AppointmentUi {
    private final IAppointmentService appointmentService;
    private final Scanner scanner;

    /**The constructor of the Appointment Ui
     * @param appointmentService is the Service for Appointment Entity
     * */
    public AppointmentUi(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
        this.scanner = new Scanner(System.in);
    }

    /**The menu for the appointment part*/
    public void appointmentMenuRun() {
        while (true) {
            try{
                appointmentMenu();
                System.out.println("*Enter your option: ");
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        addAppointment();
                        break;
                    case 2:
                        deleteAppointment();
                        break;
                    case 3:
                        updateAppointment();
                        break;
                    case 4:
                        findAppointment();
                        break;
                    case 5:
                        viewAllAppointments();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            }catch(InputMismatchException e){
                System.out.println("Invalid option");
                scanner.nextLine();
            }

        }
    }

    /**The options for the Appointment menu*/
    private void appointmentMenu() {
        System.out.println("Appointment Menu");
        System.out.println("1. Add appointment");
        System.out.println("2. Delete appointment");
        System.out.println("3. Update appointment");
        System.out.println("4. Find appointment");
        System.out.println("5. View All appointments");
        System.out.println("6. Back to main menu");

    }


    private void viewAllAppointments() {
        List<Appointment> appointments = this.appointmentService.getAllAppointment();
        if (appointments.isEmpty()) {
            System.out.println("No Appointments found.");
        } else {
            System.out.println("The appointments: ");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    private void findAppointment() {
        while (true) {
            try {
                int appointmentId = UiUtils.readingInt("Appointment id");
                Appointment appointment = this.appointmentService.getAppointment(appointmentId);
                System.out.println(appointment.toString());
                break;
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void updateAppointment() {
        while (true) {
            try {
                int idPatient = UiUtils.readingInt("Patient id");
                int idAppointment = UiUtils.readingInt("Appointment id");
                String purposeAppointment=UiUtils.readingString(" The Purpose of the appointment");
                Date date=UiUtils.readingDate();
                this.appointmentService.updateAppointment(idAppointment,idPatient,date,purposeAppointment);
                break;

            } catch (EntityException | RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteAppointment() {
        while (true) {
            try {
                int id = UiUtils.readingInt("Appointment id");
                this.appointmentService.deleteAppointment(id);
                break;
            } catch (RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addAppointment() {
        while (true) {
            try {
                Date date=UiUtils.readingDate();
                String purposeAppointment=UiUtils.readingString("Purpose of the appointment");
                int patientId = UiUtils.readingInt("Patient id");
                this.appointmentService.addAppointment(patientId,date,purposeAppointment);
                break;
            } catch (EntityException | RepositoryException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
