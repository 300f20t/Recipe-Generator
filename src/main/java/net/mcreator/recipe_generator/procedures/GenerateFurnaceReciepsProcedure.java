package net.mcreator.recipe_generator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateFurnaceReciepsProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		String preGeneratedRecipe = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_0 + ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + new Object() {
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
			RecipeGeneratorModVariables.Generated_recipe = "furnace.addRecipe(\"" + "no_name" + "\", " + preGeneratedRecipe;
		} else {
			RecipeGeneratorModVariables.Generated_recipe = "furnace.addRecipe(\"" + "" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", " + preGeneratedRecipe;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
