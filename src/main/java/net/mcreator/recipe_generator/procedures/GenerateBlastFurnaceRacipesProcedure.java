package net.mcreator.recipe_generator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateBlastFurnaceRacipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		String preGeneratedRecipe = "";
		RecipeGeneratorModVariables.item_in_slot_0_crafting_table = RecipeGeneratorModVariables.item_in_slot_0_crafting_table + ", " + RecipeGeneratorModVariables.item_in_slot_1_crafting_table + ", " + new Object() {
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
			RecipeGeneratorModVariables.Generated_recipe = "blastFurnace.addRecipe(\"" + "no_name" + "\", " + preGeneratedRecipe;
		} else {
			RecipeGeneratorModVariables.Generated_recipe = "blastFurnace.addRecipe(\"" + "" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", " + preGeneratedRecipe;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
