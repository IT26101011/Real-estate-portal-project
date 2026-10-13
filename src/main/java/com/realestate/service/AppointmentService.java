package com.realestate.service;

import com.realestate.model.Appointment;
import com.realestate.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService() {
        this.appointmentRepository = new AppointmentRepository();
    }

    // 1. CREATE: Book Appointment
    public boolean bookAppointment(Appointment appointment) {
        if (appointment == null || appointment.getAppointmentId() == null || appointment.getAppointmentId().isEmpty()) {
            return false;
        }
        appointmentRepository.save(appointment);
        return true;
    }

    // 2. READ: Get All Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // 3. UPDATE: Reschedule Appointment (New Date & Time)
    public boolean rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        List<Appointment> appointments = appointmentRepository.findAll();
        boolean found = false;

        for (Appointment app : appointments) {
            if (app.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                app.setDate(newDate);
                app.setTime(newTime);
                app.setStatus("RESCHEDULED");
                found = true;
                break;
            }
        }

        if (found) {
            appointmentRepository.saveAll(appointments);
            return true;
        }
        return false;
    }

    // 4. DELETE / CANCEL: Cancel Appointment
    public boolean cancelAppointment(String appointmentId) {
        List<Appointment> appointments = appointmentRepository.findAll();
        boolean removed = appointments.removeIf(app -> app.getAppointmentId().equalsIgnoreCase(appointmentId));

        if (removed) {
            appointmentRepository.saveAll(appointments);
            return true;
        }
        return false;
    }

    // 5. SEARCH: Filter Appointments by Client ID
    public List<Appointment> searchByClientId(String clientId) {
        List<Appointment> all = appointmentRepository.findAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment app : all) {
            if (app.getClientId().equalsIgnoreCase(clientId)) {
                result.add(app);
            }
        }
        return result;
    }

    // SEARCH: Filter Appointments by Property ID
    public List<Appointment> searchByPropertyId(String propertyId) {
        List<Appointment> all = appointmentRepository.findAll();
        List<Appointment> result = new ArrayList<>();
        for (Appointment app : all) {
            if (app.getPropertyId().equalsIgnoreCase(propertyId)) {
                result.add(app);
            }
        }
        return result;
    }
}