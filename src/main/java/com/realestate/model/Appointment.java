package com.realestate.model;

public class Appointment {
    private String appointmentId;
    private String clientId;
    private String agentId;
    private String propertyId;
    private String appointmentDate;
    private String timeSlot;
    private String status;
    private String notes;

    public Appointment() {}

    public Appointment(String appointmentId, String clientId, String agentId,
                       String propertyId, String appointmentDate,
                       String timeSlot, String status, String notes) {
        this.appointmentId = appointmentId;
        this.clientId = clientId;
        this.agentId = agentId;
        this.propertyId = propertyId;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;
        this.notes = notes;
    }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String toFileFormat() {
        return appointmentId + "," + clientId + "," + agentId + "," +
                propertyId + "," + appointmentDate + "," + timeSlot + "," +
                status + "," + notes;
    }
}