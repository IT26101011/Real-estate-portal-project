package com.realestate;

import com.realestate.model.Appointment;
import com.realestate.model.Property;
import com.realestate.repository.AppointmentRepository;
import com.realestate.service.AppointmentService;
import com.realestate.service.PropertyService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentService appointmentService = new AppointmentService();
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        PropertyService propertyService = new PropertyService();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("===========================================");
        System.out.println("     REAL ESTATE PORTAL - MAIN SYSTEM      ");
        System.out.println("===========================================");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Book New Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. Add New Property");
            System.out.println("4. View All Properties");
            System.out.println("5. Exit");
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
                    boolean success = appointmentService.bookAppointment(newApp);

                    if (success) {
                        System.out.println("SUCCESS: Appointment booked successfully and saved!");
                    } else {
                        System.out.println("FAILED: Conflict detected. Agent is already booked at this time slot!");
                    }
                    break;

                case "2":
                    System.out.println("\n--- All Scheduled Appointments ---");
                    List<Appointment> allApps = appointmentRepository.getAllAppointments();

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
                    System.out.print("Enter Property ID (e.g. P001): ");
                    String propId = scanner.nextLine();

                    System.out.print("Enter Title (e.g. Luxury Villa): ");
                    String title = scanner.nextLine();

                    System.out.print("Enter Location (e.g. Colombo): ");
                    String location = scanner.nextLine();

                    System.out.print("Enter Price (e.g. 250000.0): ");
                    double price = Double.parseDouble(scanner.nextLine());

                    System.out.print("Enter Type (e.g. House/Apartment/Land): ");
                    String type = scanner.nextLine();

                    System.out.print("Enter Bedrooms (e.g. 3): ");
                    int bedrooms = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Bathrooms (e.g. 2): ");
                    int bathrooms = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Status (e.g. Available/Sold): ");
                    String propStatus = scanner.nextLine();

                    Property newProperty = new Property(
                            propId, title, location, price, type, bedrooms, bathrooms, propStatus
                    );

                    boolean propAdded = propertyService.addProperty(newProperty);
                    if (propAdded) {
                        System.out.println("SUCCESS: Property added and saved successfully!");
                    } else {
                        System.out.println("FAILED: Could not add property.");
                    }
                    break;

                case "4":
                    System.out.println("\n--- All Registered Properties ---");
                    List<Property> allProps = propertyService.getAllProperties();

                    if (allProps.isEmpty()) {
                        System.out.println("No properties found.");
                    } else {
                        for (Property prop : allProps) {
                            System.out.println(prop);
                        }
                    }
                    break;

                case "5":
                    running = false;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option! Please enter a number between 1 and 5.");
            }
        }

        scanner.close();
    }
}