package net.mcreator.justctgui.procedures;

import net.minecraft.client.gui.widget.button.CheckboxButton;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class RecipeshapeProcedure {
	public static void execute(HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.containsKey("checkbox:Is_mirrored") ? ((CheckboxButton) guistate.get("checkbox:Is_mirrored")).func_212942_a() : false) {
			JustCtguiModVariables.Is_mirrored = true;
		} else {
			JustCtguiModVariables.Is_mirrored = false;
		}
		if (guistate.containsKey("checkbox:Is_shapeless") ? ((CheckboxButton) guistate.get("checkbox:Is_shapeless")).func_212942_a() : false) {
			JustCtguiModVariables.Is_shapeless = true;
		} else {
			JustCtguiModVariables.Is_shapeless = false;
		}
	}
}
