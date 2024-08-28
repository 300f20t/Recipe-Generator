package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class FileNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").equals("")) {
			return "generated";
		}
		return guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
	}
}
