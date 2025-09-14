package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class GetGUIOfBlockProcedure {
	public static void execute(BlockState blockWithGUI, Entity entity) {
		if (entity == null)
			return;
		if (blockWithGUI.getBlock() == Blocks.CRAFTING_TABLE) {
			{
				RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
				_vars.GUILabelsList = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).GUILabelsList + "addRecipe" + " ";
				_vars.syncPlayerVariables(entity);
			}
		} else if (blockWithGUI.getBlock() == Blocks.FURNACE) {
			{
				RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
				_vars.GUILabelsList = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).GUILabelsList + "addRecipe" + " ";
				_vars.syncPlayerVariables(entity);
			}
		} else if (blockWithGUI.getBlock() == Blocks.BLAST_FURNACE) {
			{
				RecipeGeneratorModVariables.PlayerVariables _vars = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES);
				_vars.GUILabelsList = entity.getData(RecipeGeneratorModVariables.PLAYER_VARIABLES).GUILabelsList + "addRecipe" + " ";
				_vars.syncPlayerVariables(entity);
			}
		}
	}
}