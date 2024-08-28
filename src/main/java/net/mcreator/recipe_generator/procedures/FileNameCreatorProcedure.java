package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

import java.io.File;

public class FileNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		File oldGenerated = new File("");
		double filesWithGeneratedNameCuont = 0;
		String fileName = "";
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").equals("")) {
			oldGenerated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + ("generated " + "0" + ".zs"));
			filesWithGeneratedNameCuont = 0;
			while (oldGenerated.exists()) {
				filesWithGeneratedNameCuont = filesWithGeneratedNameCuont + 1;
				oldGenerated = new File((FMLPaths.GAMEDIR.get().toString() + "/scripts"), File.separator + ("generated " + filesWithGeneratedNameCuont + ".zs"));
			}
			fileName = "generated " + filesWithGeneratedNameCuont + ".zs";
		} else {
			fileName = (guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "") + ".zs";
		}
		return fileName;
	}
}
