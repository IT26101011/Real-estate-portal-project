package com.realestate;

import com.realestate.model.Appointment;
import com.realestate.service.AppointmentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== REAL ESTATE APPOINTMENT MANAGEMENT ==========");
            System.out.println("1. Book New Appointment (Create)");
            System.out.println("2. View All Appointments (Read)");
            System.out.println("3. Reschedule Appointment (Update)");
            System.out.println("4. Cancel Appointment (Delete)");
            System.out.println("5. Search Appointments by Client ID");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Appointment ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Client ID: ");
                    String clientId = scanner.nextLine();
                    System.out.print("Enter Agent ID: ");
                    String agentId = scanner.nextLine();
                    System.out.print("Enter Property ID: ");
                    String propertyId = scanner.nextLine();
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time (HH:MM): ");
                    String time = scanner.nextLine();
                    System.out.print("Enter Status (CONFIRMED/PENDING): ");
                    String status = scanner.nextLine();
                    System.out.print("Enter Notes: ");
                    String notes = scanner.nextLine();

                    Appointment app = new Appointment(id, clientId, agentId, propertyId, date, time, status, notes);
                    if (appointmentService.bookAppointment(app)) {
                        System.out.println("✅ Appointment booked successfully!");
                    } else {
                        System.out.println("❌ Failed to book appointment.");
                    }
                    break;

                case 2:
                    List<Appointment> allApps = appointmentService.getAllAppointments();
                    System.out.println("\n--- All Appointments ---");
                    if (allApps.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        for (Appointment a : allApps) {
                            System.out.println(a);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Appointment ID to Reschedule: ");
                    String resId = scanner.nextLine();
                    System.out.print("Enter New Date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    System.out.print("Enter New Time (HH:MM): ");
                    String newTime = scanner.nextLine();

                    if (appointmentService.rescheduleAppointment(resId, newDate, newTime)) {
                        System.out.println("✅ Appointment rescheduled successfully!");
                    } else {
                        System.out.println("❌ Appointment ID not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Appointment ID to Cancel: ");
                    String cancelId = scanner.nextLine();

                    if (appointmentService.cancelAppointment(cancelId)) {
                        System.out.println("✅ Appointment canceled and removed successfully!");
                    } else {
                        System.out.println("❌ Appointment ID not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Client ID to Search: ");
                    String searchClientId = scanner.nextLine();
                    List<Appointment> clientApps = appointmentService.searchByClientId(searchClientId);

                    System.out.println("\n--- Search Results ---");
                    if (clientApps.isEmpty()) {
                        System.out.println("No appointments found for Client ID: " + searchClientId);
                    } else {
                        for (Appointment a : clientApps) {
                            System.out.println(a);
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting System... Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}