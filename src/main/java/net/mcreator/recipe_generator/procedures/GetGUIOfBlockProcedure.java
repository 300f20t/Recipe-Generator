package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

public class GetGUIOfBlockProcedure {
	public static String execute(BlockState blockWithGUI) {
		String GUIsList = "";
		if (blockWithGUI.getBlock() == Blocks.CRAFTING_TABLE) {
			GUIsList = GUIsList + "addRecipe" + " ";
		} else if (blockWithGUI.getBlock() == Blocks.FURNACE) {
			GUIsList = GUIsList + "addRecipe" + " ";
		} else if (blockWithGUI.getBlock() == Blocks.BLAST_FURNACE) {
			GUIsList = GUIsList + "addRecipe" + " ";
		}
		return GUIsList;
	}
}