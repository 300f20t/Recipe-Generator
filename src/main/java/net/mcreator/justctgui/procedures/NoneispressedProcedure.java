package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class NoneispressedProcedure {
	public static boolean execute() {
		if (("NONE").equals(JustCtguiModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
