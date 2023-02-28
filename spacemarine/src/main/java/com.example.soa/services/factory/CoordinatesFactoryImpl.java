package com.example.soa.services.factory;

import com.example.soa.model.Coordinates;
import com.example.soa.services.exceptions.BadParams;
import org.springframework.stereotype.Service;

@Service
public class CoordinatesFactoryImpl implements CoordinatesFactory {
    @Override
    public Coordinates create(Long x, Integer y) throws BadParams {
        if (x == null || y == null || x <= -282) throw new BadParams();
        return Coordinates.builder()
                .x(x)
                .y(y)
                .build();
    }
}
