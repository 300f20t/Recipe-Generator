package net.mcreator.justctgui.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.mcreator.justctgui.network.JustCtguiModVariables;


import java.util.HashMap;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

public class ScriptswriterProcedure {
    public static void execute(Entity entity, HashMap<String, Object> guistate) {
        if (entity == null || guistate == null)
            return;

        File generated;
        String fileName = guistate.containsKey("text:file_name") ? ((TextFieldWidget) guistate.get("text:file_name")).getText() : "";

        if (fileName.isEmpty()) {
            generated = new File(FMLPaths.GAMEDIR.get().toString() + "/scripts/", "generated" + new java.text.DecimalFormat("####").format(entity.getPersistentData().getDouble("generatedCount")) + ".zs");
            entity.getPersistentData().putDouble("generatedCount", entity.getPersistentData().getDouble("generatedCount") + 1);
        } else {
            generated = new File(FMLPaths.GAMEDIR.get().toString() + "/scripts/", fileName + ".zs");
        }

        try {
            // Create parent directories if they do not exist
            if (generated.getParentFile() != null) {
                generated.getParentFile().mkdirs();
            }
            // Create the file if it does not exist
            if (!generated.exists()) {
                generated.createNewFile();
            }
            // Write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(generated))) {
                writer.write(JustCtguiModVariables.Generated_recipe);
                writer.newLine();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
