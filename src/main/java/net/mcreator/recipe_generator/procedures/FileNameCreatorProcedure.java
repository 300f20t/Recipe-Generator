package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;
import java.util.Calendar;

public class FileNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		String fileName = "";
		if ((guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "").equals("")) {
			fileName = "generated " + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		} else {
			fileName = guistate.containsKey("text:file_name") ? ((EditBox) guistate.get("text:file_name")).getValue() : "";
		}
		return fileName;
	}
}
