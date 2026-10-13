package com.realestate.model;

public class Appointment {
    private String appointmentId;
    private String clientId;
    private String agentId;
    private String propertyId;
    private String date;
    private String time;
    private String status;
    private String notes;

    // Constructor
    public Appointment(String appointmentId, String clientId, String agentId, String propertyId, String date, String time, String status, String notes) {
        this.appointmentId = appointmentId;
        this.clientId = clientId;
        this.agentId = agentId;
        this.propertyId = propertyId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getAgentId() { return agentId; }
    public void setAgentId(String agentId) { this.agentId = agentId; }

    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    // Convert Appointment object to CSV format line
    public String toCSV() {
        return appointmentId + "," + clientId + "," + agentId + "," + propertyId + "," + date + "," + time + "," + status + "," + notes;
    }

    // Convert CSV line to Appointment object
    public static Appointment fromCSV(String csvLine) {
        String[] data = csvLine.split(",");
        if (data.length >= 8) {
            return new Appointment(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(), data[5].trim(), data[6].trim(), data[7].trim());
        }
        return null;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + " | Client: " + clientId + " | Agent: " + agentId +
                " | Property: " + propertyId + " | Date: " + date + " | Time: " + time +
                " | Status: " + status + " | Notes: " + notes;
    }
}