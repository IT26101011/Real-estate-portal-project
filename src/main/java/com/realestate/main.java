package com.realestate;

import com.realestate.model.Appointment;
import com.realestate.service.AppointmentService;

public class main {
    public static void main(String[] args) {
        System.out.println("=== Real Estate Portal - Appointment Management Test ===");

        AppointmentService service = new AppointmentService();

        // 1. අලුත් Appointment එකක් සාදා ගැනීම
        Appointment app1 = new Appointment(
                "A001", "C001", "AG001", "P001",
                "2026-06-15", "10:00AM-11:00AM", "Confirmed", "First client visit"
        );

        // 2. පළමු Booking එක Save කරලා බැලීම
        boolean success1 = service.bookAppointment(app1);
        if (success1) {
            System.out.println("Test 1 Passed: Appointment A001 successfully booked!");
        } else {
            System.out.println("Test 1 Failed!");
        }

        // 3. Double Booking Conflict Test එක (එම Agent ම, එම දිනයේම, එම Time Slot එකේදීම)
        Appointment app2 = new Appointment(
                "A002", "C002", "AG001", "P002",
                "2026-06-15", "10:00AM-11:00AM", "Pending", "Conflicting appointment"
        );

        System.out.println("\n--- Testing Double Booking Prevention ---");
        boolean success2 = service.bookAppointment(app2);
        if (!success2) {
            System.out.println("Test 2 Passed: Conflict Matrix successfully blocked the double booking!");
        } else {
            System.out.println("Test 2 Failed: Double booking was allowed unexpectedly!");
        }
    }
}