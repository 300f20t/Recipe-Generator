package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class HorizontalispressedProcedure {
	public static boolean execute() {
		if (("HORIZONTAL").equals(JustCtguiModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
