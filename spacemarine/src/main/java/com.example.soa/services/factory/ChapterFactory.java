package com.example.soa.services.factory;

import com.example.soa.model.Chapter;
import com.example.soa.services.exceptions.BadParams;

public interface ChapterFactory {
    Chapter create(String name, String world) throws BadParams;
}
