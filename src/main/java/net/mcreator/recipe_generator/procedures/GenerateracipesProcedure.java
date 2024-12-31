package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class GenerateracipesProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		List<Object> KubeJSItemShapeArray = new ArrayList<>();
		double i = 0;
		String preGeneratedRecipe = "";
		String recipeType = "";
		String recipeTypeFutures = "";
		String KubeJSRecipeShape = "";
		if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {
			preGeneratedRecipe = RecipeGeneratorModVariables.item_in_slot_9 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", [" + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_3 + ", " + RecipeGeneratorModVariables.item_in_slot_4 + ", "
					+ RecipeGeneratorModVariables.item_in_slot_5 + "], " + "\n" + "[" + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", " + RecipeGeneratorModVariables.item_in_slot_8 + "]]);";
			if (guistate.containsKey("checkbox:Is_mirrored") && ((Checkbox) guistate.get("checkbox:Is_mirrored")).selected()) {
				recipeType = "craftingTable.addShapedMirrored(\"";
				recipeTypeFutures = "MirrorAxis." + RecipeGeneratorModVariables.Mirror_axis + ", ";
			} else if (guistate.containsKey("checkbox:Is_shapeless") && ((Checkbox) guistate.get("checkbox:Is_shapeless")).selected()) {
				recipeType = "craftingTable.addShapeless(\"";
				recipeTypeFutures = preGeneratedRecipe.replace("<item:minecraft:air>, ", "");
			} else {
				recipeType = "craftingTable.addShaped(\"";
				recipeTypeFutures = preGeneratedRecipe;
			}
			RecipeGeneratorModVariables.Generated_recipe = recipeType + ""
					+ ((guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : "").isEmpty()
							? RecipeNameCreatorProcedure.execute(guistate)
							: (guistate.containsKey("text:recipe_name") ? ((EditBox) guistate.get("text:recipe_name")).getValue() : ""))
					+ "\", " + recipeTypeFutures;
		} else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {
			KubeJSItemShapeArray.add("A:");
			KubeJSItemShapeArray.add("B:");
			KubeJSItemShapeArray.add("C:");
			KubeJSItemShapeArray.add("D:");
			KubeJSItemShapeArray.add("E:");
			KubeJSItemShapeArray.add("F:");
			KubeJSItemShapeArray.add("G:");
			KubeJSItemShapeArray.add("H:");
			KubeJSItemShapeArray.add("I:");
			preGeneratedRecipe = "\n" + ((KubeJSItemShapeArray.get(0) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_0 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(1) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_1 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(2) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_2 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(3) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_3 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(4) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_4 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(5) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_5 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(6) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_6 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(7) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_7 + ",") + "\n"
					+ ((KubeJSItemShapeArray.get(8) instanceof String _s ? _s : "") + "" + RecipeGeneratorModVariables.item_in_slot_8 + ",") + "\n";
			i = 0;
			while (i <= 8) {
				if (preGeneratedRecipe.contains((KubeJSItemShapeArray.get((int) i) instanceof String _s ? _s : "") + "'minecraft:air',")) {
					preGeneratedRecipe = preGeneratedRecipe.replace((KubeJSItemShapeArray.get((int) i) instanceof String _s ? _s : "") + "'minecraft:air',", " ");
					KubeJSRecipeShape = "'" + (!(RecipeGeneratorModVariables.item_in_slot_0).equals("'minecraft:air'") ? "A" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_1).equals("'minecraft:air'") ? "B" : " ")
							+ (!(RecipeGeneratorModVariables.item_in_slot_2).equals("'minecraft:air'") ? "C" : " ") + "'," + "\n" + "'" + (!(RecipeGeneratorModVariables.item_in_slot_3).equals("'minecraft:air'") ? "D" : " ")
							+ (!(RecipeGeneratorModVariables.item_in_slot_4).equals("'minecraft:air'") ? "E" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_5).equals("'minecraft:air'") ? "F" : " ") + "'," + "\n" + "'"
							+ (!(RecipeGeneratorModVariables.item_in_slot_6).equals("'minecraft:air'") ? "G" : " ") + (!(RecipeGeneratorModVariables.item_in_slot_7).equals("'minecraft:air'") ? "H" : " ")
							+ (!(RecipeGeneratorModVariables.item_in_slot_8).equals("'minecraft:air'") ? "I" : " ") + "'";
				}
				i = i + 1;
			}
			RecipeGeneratorModVariables.Generated_recipe = "ServerEvents.recipes(event => {" + "event.shaped(" + "\n" + " Item.of(" + RecipeGeneratorModVariables.item_in_slot_9 + ", "
					+ new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ")," + "\n" + " [" + "\n" + KubeJSRecipeShape + "\n" + " ]," + "\n" + " {" + preGeneratedRecipe + "})})";
		}
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
