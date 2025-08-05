package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;
import net.mcreator.recipe_generator.init.RecipeGeneratorModMenus;

public class CraftingTableAddShapelessCraftTweakerProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		RecipeGeneratorModVariables.Generated_recipe = "craftingTable.addShapeless(\"" + ""
				+ (((entity instanceof Player _entity0 && _entity0.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu0) ? _menu0.getMenuState(0, "recipe_name", "") : "")
						.isEmpty() ? RecipeNameCreatorProcedure.execute(entity) : ((entity instanceof Player _entity1 && _entity1.containerMenu instanceof RecipeGeneratorModMenus.MenuAccessor _menu1) ? _menu1.getMenuState(0, "recipe_name", "") : ""))
				+ "\", " + RecipeGeneratorModVariables.item_in_slot_9
				+ ((" * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", " + "\n" + "["
						+ (RecipeGeneratorModVariables.item_in_slot_0.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_0 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_1.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_1 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_2.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_2 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_3.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_3 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_4.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_4 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_5.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_5 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_6.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_6 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_7.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_7 + ", ")
						+ (RecipeGeneratorModVariables.item_in_slot_8.contains("<item:minecraft:air>, ") ? "" : RecipeGeneratorModVariables.item_in_slot_8 + ", ") + ");").replace("<item:minecraft:air>, ", "")).replace(">, );", ">]);");
	}
}