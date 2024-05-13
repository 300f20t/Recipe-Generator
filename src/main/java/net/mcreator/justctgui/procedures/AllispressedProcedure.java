package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class AllispressedProcedure {
	public static boolean execute() {
		if (("ALL").equals(JustCtguiModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
