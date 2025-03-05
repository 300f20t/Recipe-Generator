package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.Minecraft;
import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ScriptswriterProcedure {
    public ScriptswriterProcedure() {
    }

    public static void execute(HashMap guistate) {
        if (guistate != null) {
            String localDir = "";
            String fileExtension = "";
            String fileName = "";

            if (RecipeGeneratorModVariables.selectedMethod.equals("CraftTweaker")) {
                localDir = "/scripts";
                fileExtension = ".zs";
            } else if (RecipeGeneratorModVariables.selectedMethod.equals("KubeJS")) {
                localDir = "/kubejs/server_scripts";
                fileExtension = ".js";
            }

            fileName = guistate.containsKey("text:file_name") ? (String) guistate.get("text:file_name") : "";

            if (fileName.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                fileName = "generated_" + dateFormat.format(Calendar.getInstance().getTime());
            }

            File generated = new File(Minecraft.getInstance().gameDirectory, localDir + "/" + fileName + fileExtension);
            File parentDir = generated.getParentFile();

            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            String fileContent = RecipeGeneratorModVariables.Generated_recipe;

            try (FileWriter writer = new FileWriter(generated)) {
                writer.write(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
