package net.mcreator.justctgui.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		File generated = new File("");
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").isEmpty()) {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts/"), File.separator + ("generated" + new java.text.DecimalFormat("####").format(JustCtguiModVariables.generated_count) + ".zs"));
			JustCtguiModVariables.generated_count = JustCtguiModVariables.generated_count + 1;
		} else {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts/"), File.separator + ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "") + ".zs"));
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
