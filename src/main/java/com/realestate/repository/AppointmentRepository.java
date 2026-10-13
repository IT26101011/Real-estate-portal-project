package com.realestate.repository;

import com.realestate.model.Appointment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private static final String FILE_PATH = "appointments.txt";

    // Create / Save single appointment
    public void save(Appointment appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(appointment.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    // Read / Find all appointments
    public List<Appointment> findAll() {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return appointments;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Appointment appointment = Appointment.fromCSV(line);
                if (appointment != null) {
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading appointments: " + e.getMessage());
        }
        return appointments;
    }

    // Update / Delete - Rewrite all appointments back to file
    public void saveAll(List<Appointment> appointments) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Appointment app : appointments) {
                writer.write(app.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating appointments file: " + e.getMessage());
        }
    }
}