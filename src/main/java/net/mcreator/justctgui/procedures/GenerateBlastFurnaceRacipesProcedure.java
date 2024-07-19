package net.mcreator.justctgui.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class GenerateBlastFurnaceRacipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		JustCtguiModVariables.Pre_generated_recipe = JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", " + new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:XP") ? ((EditBox) guistate.get("text:XP")).getValue() : "") + ", " + Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:time") ? ((EditBox) guistate.get("text:time")).getValue() : "")) + ");";
		if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()) {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.BlastFurnaceRecipeManager;" + "blastFurnace.addRecipe(\"" + "no_name" + "\", " + JustCtguiModVariables.Pre_generated_recipe;
		} else {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.BlastFurnaceRecipeManager;" + "blastFurnace.addRecipe(\"" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "")
					+ "\", " + JustCtguiModVariables.Pre_generated_recipe;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(JustCtguiModVariables.Generated_recipe), false);
	}
}
