package io.github.ftincdev.recipe_generator.client.platform;

import io.github.ftincdev.recipe_generator.platform.services.IFileSaver;
import net.neoforged.fml.loading.FMLPaths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NeoForgeFileSaver implements IFileSaver {
    private static final Path GAME_DIR = FMLPaths.GAMEDIR.get();

    @Override
    public void save(String content, String fileName, String subFolder) {
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
