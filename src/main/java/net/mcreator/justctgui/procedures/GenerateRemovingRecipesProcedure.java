package net.mcreator.justctgui.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;

import net.mcreator.justctgui.network.JustCtguiModVariables;

public class GenerateRemovingRecipesProcedure {
	public static void execute(IWorld world) {
		JustCtguiModVariables.Generated_recipe = "craftingTable.remove(" + JustCtguiModVariables.item_in_slot_0_crafting_table + ");";
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null)
			world.getWorld().getServer().getPlayerList().sendMessage(new StringTextComponent(JustCtguiModVariables.Generated_recipe));
	}
}
