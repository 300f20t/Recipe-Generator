package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class ATTENTIONProcedure {
	public static boolean execute() {
		if (JustCtguiModVariables.Is_mirrored) {
			if (JustCtguiModVariables.Is_shapeless) {
				return true;
			}
		}
		return false;
	}
}
