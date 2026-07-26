package com.recipe_generator.client.util;

import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaver {

    private static final Path GAME_DIR = FabricLoader.getInstance().getGameDir();

    public static void save(String content, String fileName, String subFolder) {
        try {
            Path dir = GAME_DIR.resolve(subFolder);
            Files.createDirectories(dir);

            Path file = dir.resolve(fileName);
            Files.writeString(file, content);

            System.out.println("Saved: " + file.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
