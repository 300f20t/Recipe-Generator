
package net.mcreator.recipe_generator.command;

import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandBuildContext;

import net.mcreator.recipe_generator.procedures.OpencraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesCraftingTableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenMethodSelectProcedure;
import net.mcreator.recipe_generator.procedures.OpenFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceRemovingCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.DebugVariableSelectedMethodShowProcedure;
import net.mcreator.recipe_generator.procedures.DebugJsonSerializerProcedure;

import com.mojang.brigadier.CommandDispatcher;

public class RguiCommandCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext, Commands.CommandSelection environment) {
		dispatcher.register(Commands.literal("rgui").requires(s -> s.hasPermission(4)).then(Commands.literal("debug").then(Commands.literal("jsonSerializer").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			DebugJsonSerializerProcedure.execute();
			return 0;
		})).then(Commands.literal("selectedMethod").then(Commands.literal("show").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			DebugVariableSelectedMethodShowProcedure.execute(entity);
			return 0;
		})))).then(Commands.literal("generationMethode").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenMethodSelectProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("addRecipe").then(Commands.literal("craftingTable").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpencraftingtableCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("furnace").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("blastFurnace").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenBlastFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))).then(Commands.literal("remove").then(Commands.literal("craftingTable").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenRemovingRecipesCraftingTableCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("furnace").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenRemovingRecipesFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("blastFurnace").executes(arguments -> {
			ServerLevel world = arguments.getSource().getLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenBlastFurnaceRemovingCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))));
	}
}
