package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.StringArgumentType;

public class DebugVariableSelectedMethodChangeProcedure {
	public static void execute(LevelAccessor world, CommandContext<CommandSourceStack> arguments) {
		RecipeGeneratorModVariables.WorldVariables.get(world).selectedMethod = StringArgumentType.getString(arguments, "value");
		RecipeGeneratorModVariables.WorldVariables.get(world).syncData(world);
	}
}
