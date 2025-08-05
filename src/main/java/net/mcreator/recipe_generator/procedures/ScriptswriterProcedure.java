package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

public class ScriptswriterProcedure {
public static void execute(
LevelAccessor world,
Entity entity ) {
if (
entity == null ) return ;
String localDir = "";String fileExtention = "";
FileNameCreatorProcedure.execute(entity)
;
if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("CraftTweaker")) {localDir = "/scripts";fileExtention = ".zs";}else if ((RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod).equals("KubeJS")) {localDir = "/kubejs/server_scripts";fileExtention = ".js";}if (!) {}
}
}