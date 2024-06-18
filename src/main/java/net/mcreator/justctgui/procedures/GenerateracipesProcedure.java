package net.mcreator.justctgui.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.justctgui.network.JustCtguiModVariables;

import java.util.HashMap;

public class GenerateracipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
			JustCtguiModVariables.Is_shapeless = true;
		} else {
			JustCtguiModVariables.Is_shapeless = false;
		}
		if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
			JustCtguiModVariables.Is_mirrored = true;
		} else {
			JustCtguiModVariables.Is_mirrored = false;
		}
		JustCtguiModVariables.Pre_generated_recipe = JustCtguiModVariables.Is_shapeless
				? (JustCtguiModVariables.item_in_slot_9_crafting_table + ", [" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + ", " + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + ", " + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]);").replace("<item:minecraft:air>, ", "")
				: JustCtguiModVariables.item_in_slot_9_crafting_table + ", [[" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]]);";
		if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty() && !JustCtguiModVariables.Is_shapeless && !JustCtguiModVariables.Is_mirrored) {
			JustCtguiModVariables.Generated_recipe = "craftingTable.addShaped(\"" + "no_name" + new java.text.DecimalFormat("##.##").format(JustCtguiModVariables.generated_count) + "\", " + JustCtguiModVariables.Pre_generated_recipe;
		} else if (!JustCtguiModVariables.Is_shapeless && !JustCtguiModVariables.Is_mirrored) {
			JustCtguiModVariables.Generated_recipe = "craftingTable.addShaped(\"" + "" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", " + JustCtguiModVariables.Pre_generated_recipe;
		} else if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty() && JustCtguiModVariables.Is_shapeless) {
			JustCtguiModVariables.Generated_recipe = "craftingTable.addShapeless(\"" + "no_name" + new java.text.DecimalFormat("##.##").format(JustCtguiModVariables.generated_count) + "\", " + JustCtguiModVariables.Pre_generated_recipe;
		} else if (JustCtguiModVariables.Is_shapeless) {
			JustCtguiModVariables.Generated_recipe = "craftingTable.addShapeless(\"" + "" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "") + "\", "
					+ JustCtguiModVariables.Pre_generated_recipe;
		} else if ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty() && JustCtguiModVariables.Is_mirrored) {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;" + "craftingTable.addShapedMirrored(\"" + "no_name" + new java.text.DecimalFormat("##.##").format(JustCtguiModVariables.generated_count) + "\", "
					+ "MirrorAxis." + JustCtguiModVariables.Mirror_axis + ", " + JustCtguiModVariables.Pre_generated_recipe;
		} else if (JustCtguiModVariables.Is_mirrored) {
			JustCtguiModVariables.Generated_recipe = "import crafttweaker.api.recipe.MirrorAxis;" + "craftingTable.addShapedMirrored(\"" + (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "")
					+ "\", " + "MirrorAxis." + JustCtguiModVariables.Mirror_axis + ", " + JustCtguiModVariables.Pre_generated_recipe;
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(JustCtguiModVariables.Generated_recipe), false);
	}
}
