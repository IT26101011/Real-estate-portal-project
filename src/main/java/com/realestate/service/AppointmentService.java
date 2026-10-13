package com.realestate.service;

import com.realestate.model.Appointment;
import com.realestate.repository.AppointmentRepository;
import java.util.List;

public class AppointmentService {
    private final AppointmentRepository repository = new AppointmentRepository();

    public boolean isSlotAvailable(String agentId, String date, String timeSlot) {
        List<Appointment> allAppointments = repository.getAllAppointments();

        for (Appointment app : allAppointments) {
            if (app.getStatus().equalsIgnoreCase("Cancelled")) {
                continue;
            }

            boolean isSameAgent = app.getAgentId().equalsIgnoreCase(agentId);
            boolean isSameDate = app.getAppointmentDate().equalsIgnoreCase(date);
            boolean isSameSlot = app.getTimeSlot().equalsIgnoreCase(timeSlot);

            if (isSameAgent && isSameDate && isSameSlot) {
                return false;
            }
        }
        return true;
    }

    public boolean bookAppointment(Appointment newApp) {
        boolean available = isSlotAvailable(
                newApp.getAgentId(),
                newApp.getAppointmentDate(),
                newApp.getTimeSlot()
        );

        if (available) {
            return repository.saveAppointment(newApp);
        } else {
            System.err.println("Booking Failed: Selected Time Slot is already booked for this Agent!");
            return false;
        }
    }
}