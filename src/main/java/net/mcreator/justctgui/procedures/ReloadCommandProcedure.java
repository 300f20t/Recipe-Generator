package net.mcreator.justctgui.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

public class ReloadCommandProcedure {
	public static void execute(IWorld world, double x, double y, double z) {
		if (world.getWorld() instanceof ServerWorld)
			((ServerWorld) world.getWorld()).getServer().getCommandManager().handleCommand(
					new CommandSource(ICommandSource.field_213139_a_, new Vec3d(x, y, z), Vec2f.ZERO, ((ServerWorld) world.getWorld()), 4, "", new StringTextComponent(""), ((ServerWorld) world.getWorld()).getServer(), null).withFeedbackDisabled(),
					"reload");
	}
}
