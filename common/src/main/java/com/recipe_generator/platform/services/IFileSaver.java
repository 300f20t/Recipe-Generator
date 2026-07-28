package com.recipe_generator.platform.services;

public interface IFileSaver {
    void save(String content, String fileName, String subFolder);
}