package net.mcreator.recipe_generator.procedures;

import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;
import java.util.Calendar;

public class FileNameCreatorProcedure {
	public static String execute(HashMap guistate) {
		if (guistate == null)
			return "";
		String fileName = "";
		fileName = guistate.containsKey("text:textFieldName") ? ((EditBox) guistate.get("text:textFieldName")).getValue() : "";
		if ((fileName).isEmpty()) {
			fileName = "generated " + new java.text.SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(Calendar.getInstance().getTime());
		}
		return fileName;
	}
}
