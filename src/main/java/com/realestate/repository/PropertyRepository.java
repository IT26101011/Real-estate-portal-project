package com.realestate.repository;

import com.realestate.model.Property;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyRepository {
    private static final String FILE_PATH = "properties.txt";

    // Save a new property to file
    public void save(Property property) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(property.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving property: " + e.getMessage());
        }
    }

    // Find all properties from file
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return properties;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Property property = Property.fromCSV(line);
                if (property != null) {
                    properties.add(property);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading properties: " + e.getMessage());
        }
        return properties;
    }
}