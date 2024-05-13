package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class VerticalispressedProcedure {
	public static boolean execute() {
		if (("VERTICAL").equals(JustCtguiModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
