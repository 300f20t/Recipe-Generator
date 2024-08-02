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
		JustCtguiModVariables.Pre_generated_recipe = guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()
				? (JustCtguiModVariables.item_in_slot_9_crafting_table + ", [" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + ", " + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + ", " + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]);").replace("<item:minecraft:air>, ", "")
				: JustCtguiModVariables.item_in_slot_9_crafting_table + ", [[" + JustCtguiModVariables.item_in_slot_0_crafting_table + ", " + JustCtguiModVariables.item_in_slot_1_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_2_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_3_crafting_table + ", " + JustCtguiModVariables.item_in_slot_4_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_5_crafting_table + "], [" + JustCtguiModVariables.item_in_slot_6_crafting_table + ", " + JustCtguiModVariables.item_in_slot_7_crafting_table + ", "
						+ JustCtguiModVariables.item_in_slot_8_crafting_table + "]]);";
		JustCtguiModVariables.Generated_recipe = (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected() ? "import crafttweaker.api.recipe.MirrorAxis;" + "craftingTable.addShapedMirrored(\"" : "")
				+ "" + (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected() ? "craftingTable.addShapeless(\"" : "")
				+ (!(guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected() || guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected())
						? "craftingTable.addShaped(\""
						: "")
				+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
						? "no_name"
						: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
				+ "\", " + (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected() ? "MirrorAxis." + JustCtguiModVariables.Mirror_axis + ", " : "") + JustCtguiModVariables.Pre_generated_recipe;
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(JustCtguiModVariables.Generated_recipe), false);
	}
}
