
package net.mcreator.recipe_generator.command;

import org.checkerframework.checker.units.qual.s;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.commands.Commands;

import net.mcreator.recipe_generator.procedures.OpencraftingtableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesCraftingTableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceRemovingCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceCTGUIProcedure;

@Mod.EventBusSubscriber
public class CtguicommandCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("rgui").requires(s -> s.hasPermission(4)).then(Commands.literal("craftingTable").then(Commands.literal("addRecipe").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpencraftingtableCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("remove").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenRemovingRecipesCraftingTableCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))).then(Commands.literal("furnace").then(Commands.literal("addRecipe").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("remove").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenRemovingRecipesFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))).then(Commands.literal("blastFurnace").then(Commands.literal("addRecipe").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenBlastFurnaceCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("remove").executes(arguments -> {
			Level world = arguments.getSource().getUnsidedLevel();
			double x = arguments.getSource().getPosition().x();
			double y = arguments.getSource().getPosition().y();
			double z = arguments.getSource().getPosition().z();
			Entity entity = arguments.getSource().getEntity();
			if (entity == null && world instanceof ServerLevel _servLevel)
				entity = FakePlayerFactory.getMinecraft(_servLevel);
			Direction direction = Direction.DOWN;
			if (entity != null)
				direction = entity.getDirection();

			OpenBlastFurnaceRemovingCTGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))));
	}
}
