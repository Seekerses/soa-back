package com.example.soa.services.factory;

import com.example.soa.model.Coordinates;
import com.example.soa.services.exceptions.BadParams;

public interface CoordinatesFactory {
    Coordinates create(Long x, Integer y) throws BadParams;
}
