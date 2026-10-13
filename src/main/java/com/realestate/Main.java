package com.realestate;

import com.realestate.model.Appointment;
import com.realestate.repository.AppointmentRepository;
import com.realestate.service.AppointmentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentService service = new AppointmentService();
        AppointmentRepository repository = new AppointmentRepository();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===========================================");
        System.out.println("  REAL ESTATE APPOINTMENT MANAGEMENT SYSTEM");
        System.out.println("===========================================");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Book New Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Appointment ID (e.g. A003): ");
                    String appointmentId = scanner.nextLine();

                    System.out.print("Enter Client ID (e.g. C001): ");
                    String clientId = scanner.nextLine();

                    System.out.print("Enter Agent ID (e.g. AG001): ");
                    String agentId = scanner.nextLine();

                    System.out.print("Enter Property ID (e.g. P001): ");
                    String propertyId = scanner.nextLine();

                    System.out.print("Enter Date (e.g. 2026-06-15): ");
                    String appointmentDate = scanner.nextLine();

                    System.out.print("Enter Time Slot (e.g. 10:00AM-11:00AM): ");
                    String timeSlot = scanner.nextLine();

                    System.out.print("Enter Status (e.g. Confirmed/Pending): ");
                    String status = scanner.nextLine();

                    System.out.print("Enter Notes (e.g. First visit): ");
                    String notes = scanner.nextLine();

                    Appointment newApp = new Appointment(
                            appointmentId, clientId, agentId, propertyId,
                            appointmentDate, timeSlot, status, notes
                    );

                    System.out.println("\nProcessing booking...");
                    boolean success = service.bookAppointment(newApp);

                    if (success) {
                        System.out.println("SUCCESS: Appointment booked successfully and saved!");
                    } else {
                        System.out.println("FAILED: Conflict detected. Agent is already booked at this time slot!");
                    }
                    break;

                case "2":
                    System.out.println("\n--- All Scheduled Appointments ---");
                    List<Appointment> allApps = repository.getAllAppointments();

                    if (allApps.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        for (Appointment app : allApps) {
                            System.out.println("ID: " + app.getAppointmentId() +
                                    " | Client: " + app.getClientId() +
                                    " | Agent: " + app.getAgentId() +
                                    " | Date: " + app.getAppointmentDate() +
                                    " | Slot: " + app.getTimeSlot() +
                                    " | Status: " + app.getStatus());
                        }
                    }
                    break;

                case "3":
                    running = false;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }
}