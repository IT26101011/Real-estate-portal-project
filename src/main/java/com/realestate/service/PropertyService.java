package com.realestate.service;

import com.realestate.model.Property;
import com.realestate.repository.PropertyRepository;

import java.util.List;

public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService() {
        this.propertyRepository = new PropertyRepository();
    }

    // Add a new property
    public boolean addProperty(Property property) {
        if (property == null || property.getPropertyId() == null || property.getPropertyId().isEmpty()) {
            return false;
        }
        propertyRepository.save(property);
        return true;
    }

    // Get all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}
