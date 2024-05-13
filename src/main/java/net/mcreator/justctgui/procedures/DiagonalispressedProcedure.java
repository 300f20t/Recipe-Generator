package net.mcreator.justctgui.procedures;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class DiagonalispressedProcedure {
	public static boolean execute() {
		if (("DIAGONAL").equals(JustCtguiModVariables.Mirror_axis)) {
			return true;
		}
		return false;
	}
}
