package com.realestate.repository;

import com.realestate.model.Appointment;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {
    private final String filePath = "appointments.txt";

    public boolean saveAppointment(Appointment appointment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(appointment.toFileFormat());
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 8) {
                    Appointment app = new Appointment(
                            data[0], data[1], data[2], data[3],
                            data[4], data[5], data[6], data[7]
                    );
                    list.add(app);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return list;
    }
}