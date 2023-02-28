package com.example.soa.services.factory;

import com.example.soa.model.Chapter;
import com.example.soa.services.exceptions.BadParams;
import org.springframework.stereotype.Service;

@Service
public class ChapterFactoryImpl implements ChapterFactory {
    @Override
    public Chapter create(String name, String world) throws BadParams {
        if (name == null || name.equals("")) throw new BadParams();
        return Chapter.builder()
                .chapterName(name)
                .world(world)
                .build();
    }
}
