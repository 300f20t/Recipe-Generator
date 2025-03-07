package net.mcreator.recipe_generator.procedures;

import net.fabricmc.loader.api.FabricLoader;
import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.nio.file.Path;

public class ScriptswriterProcedure {
    public static void execute(HashMap guistate) {

        if (guistate == null) {
            return;
        }

        String localDir = "";
        String fileExtension = "";
        String fileName = "";

        if ("CraftTweaker".equals(RecipeGeneratorModVariables.selectedMethod)) {
            localDir = "/scripts";
            fileExtension = ".zs";
        } else if ("KubeJS".equals(RecipeGeneratorModVariables.selectedMethod)) {
            localDir = "/kubejs/server_scripts";
            fileExtension = ".js";
        } else {
            return;
        }

        Object fileNameObject = guistate.get("text:file_name");

        if (fileNameObject instanceof String) {
            fileName = (String) fileNameObject;
        } else if (fileNameObject != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            fileName = "generated_" + dateFormat.format(Calendar.getInstance().getTime());
        }

        if (fileName.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); 
            fileName = "generated_" + dateFormat.format(Calendar.getInstance().getTime());
        }

        Path gameDirPath = FabricLoader.getInstance().getGameDir();
        File gameDir = gameDirPath.toFile();

        File generated = new File(gameDir, localDir + "/" + fileName + fileExtension);

        File parentDir = generated.getParentFile();
        if (!parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs(); 
        }

        String fileContent = RecipeGeneratorModVariables.Generated_recipe;

        if (fileContent == null || fileContent.isEmpty()) {
            return;
        }

        try (FileWriter writer = new FileWriter(generated)) {
            writer.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
