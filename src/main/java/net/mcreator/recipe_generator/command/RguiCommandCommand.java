
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
import net.mcreator.recipe_generator.procedures.OpenSmithingRGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenRemovingRecipesCraftingTableCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenMethodSelectProcedure;
import net.mcreator.recipe_generator.procedures.OpenFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceRemovingCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenBlastFurnaceCTGUIProcedure;
import net.mcreator.recipe_generator.procedures.OpenAvaritaCraftingTableRGUIProcedure;
import net.mcreator.recipe_generator.procedures.DebugVariableSelectedMethodShowProcedure;
import net.mcreator.recipe_generator.procedures.DebugJsonSerializerProcedure;
import net.mcreator.recipe_generator.procedures.DebugGameDIrProcedure;

@Mod.EventBusSubscriber
public class RguiCommandCommand {
	@SubscribeEvent
	public static void registerCommand(RegisterCommandsEvent event) {
		event.getDispatcher().register(Commands.literal("rgui").requires(s -> s.hasPermission(4)).then(Commands.literal("debug").then(Commands.literal("gameDir").executes(arguments -> {
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

			DebugGameDIrProcedure.execute(entity);
			return 0;
		})).then(Commands.literal("jsonSerializer").executes(arguments -> {
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

			DebugJsonSerializerProcedure.execute();
			return 0;
		})).then(Commands.literal("selectedMethod").then(Commands.literal("show").executes(arguments -> {
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

			DebugVariableSelectedMethodShowProcedure.execute(world, entity);
			return 0;
		})))).then(Commands.literal("generationMethode").executes(arguments -> {
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

			OpenMethodSelectProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("addRecipe").then(Commands.literal("craftingTable").executes(arguments -> {
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
		})).then(Commands.literal("avaritaCraftingTable").executes(arguments -> {
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

			OpenAvaritaCraftingTableRGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		})).then(Commands.literal("furnace").executes(arguments -> {
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
		})).then(Commands.literal("blastFurnace").executes(arguments -> {
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
		}))).then(Commands.literal("addTransformRecipe").then(Commands.literal("smithing").executes(arguments -> {
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

			OpenSmithingRGUIProcedure.execute(world, x, y, z, entity);
			return 0;
		}))).then(Commands.literal("remove").then(Commands.literal("craftingTable").executes(arguments -> {
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
		})).then(Commands.literal("furnace").executes(arguments -> {
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
		})).then(Commands.literal("blastFurnace").executes(arguments -> {
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
