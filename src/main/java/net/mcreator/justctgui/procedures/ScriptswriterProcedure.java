package net.mcreator.justctgui.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.client.gui.components.EditBox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

import java.io.IOException;
import java.io.File;

public class ScriptswriterProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		File generated = new File("");
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").isEmpty()) {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts/"), File.separator + ("generated" + new java.text.DecimalFormat("##.##").format(JustCtguiModVariables.generated_count) + ".zs"));
		} else {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts/"), File.separator + ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "") + ".zs"));
		}
		try {
			generated.getParentFile().mkdirs();
			generated.createNewFile();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
