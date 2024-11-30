package net.mcreator.recipe_generator.procedures;

import net.neoforged.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

import java.io.File;

public class ScriptswriterProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		File generated = new File("");
		FileNameCreatorProcedure.execute(guistate);
		if ((RecipeGeneratorModVariables.selectedMethod).equals("CraftTweaker")) {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "scripts/"), File.separator + (FileNameCreatorProcedure.execute(guistate) + ".zs"));
			{
				generated.write(RecipeGeneratorModVariables.Generated_recipe);
				generated.newLine();
			}
		} else if ((RecipeGeneratorModVariables.selectedMethod).equals("KubeJS")) {
			generated = new File((FMLPaths.GAMEDIR.get().toString() + "server_scripts/"), File.separator + (FileNameCreatorProcedure.execute(guistate) + ".zs"));
			{
				generated.write(RecipeGeneratorModVariables.Generated_recipe);
				generated.newLine();
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("\u00A7cError! Generation method not selected!"), false);
		}
	}
}
