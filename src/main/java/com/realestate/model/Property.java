package com.realestate.model;

public class Property {
    private String propertyId;
    private String title;
    private String location;
    private double price;
    private String type; // House, Apartment, Land, etc.
    private int bedrooms;
    private int bathrooms;
    private String status; // Available, Sold, Rented

    public Property(String propertyId, String title, String location, double price, String type, int bedrooms, int bathrooms, String status) {
        this.propertyId = propertyId;
        this.title = title;
        this.location = location;
        this.price = price;
        this.type = type;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.status = status;
    }

    // Getters and Setters
    public String getPropertyId() { return propertyId; }
    public void setPropertyId(String propertyId) { this.propertyId = propertyId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }

    public int getBathrooms() { return bathrooms; }
    public void setBathrooms(int bathrooms) { this.bathrooms = bathrooms; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // To CSV format for file persistence
    public String toCSV() {
        return propertyId + "," + title + "," + location + "," + price + "," + type + "," + bedrooms + "," + bathrooms + "," + status;
    }

    // From CSV format
    public static Property fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 8) {
            return new Property(
                    parts[0], parts[1], parts[2],
                    Double.parseDouble(parts[3]),
                    parts[4],
                    Integer.parseInt(parts[5]),
                    Integer.parseInt(parts[6]),
                    parts[7]
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID: " + propertyId + " | Title: " + title + " | Location: " + location +
                " | Price: $" + price + " | Type: " + type + " | Beds: " + bedrooms +
                " | Baths: " + bathrooms + " | Status: " + status;
    }
}
