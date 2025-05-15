package net.mcreator.recipe_generator.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateFurnaceReciepsProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String preGeneratedRecipe = "";
		preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_0 + ", " + (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:XP") ? ((EditBox) guistate.get("text:XP")).getValue() : "") > 0 ? new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:XP") ? ((EditBox) guistate.get("text:XP")).getValue() : "") : 1) + ", " + (Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:time") ? ((EditBox) guistate.get("text:time")).getValue() : "")) > 0 ? Math.round(new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:time") ? ((EditBox) guistate.get("text:time")).getValue() : "")) : Math.round(10)) + ");";
		RecipeGeneratorModVariables.Generated_recipe = "furnace.addRecipe(\"" + ""
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? RecipeNameCreatorProcedure.execute(guistate)
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + preGeneratedRecipe;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
