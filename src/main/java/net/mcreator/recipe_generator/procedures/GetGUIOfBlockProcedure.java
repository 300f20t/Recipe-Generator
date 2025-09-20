package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GetGUIOfBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CRAFTING_TABLE) {
			RecipeGeneratorModVariables.GUILabelsList.add("addRecipe");
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.FURNACE) {
			RecipeGeneratorModVariables.GUILabelsList.add("addRecipe");
		} else if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.BLAST_FURNACE) {
			RecipeGeneratorModVariables.GUILabelsList.add("addRecipe");
		}
	}
}