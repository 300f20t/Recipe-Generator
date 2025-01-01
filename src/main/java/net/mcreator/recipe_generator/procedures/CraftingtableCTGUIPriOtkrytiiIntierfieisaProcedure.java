package net.mcreator.recipe_generator.procedures;

import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import io.netty.buffer.Unpooled;

public class CraftingtableCTGUIPriOtkrytiiIntierfieisaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!RecipeGeneratorModVariables.methodSelected) {
			if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof CraftingtableCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "CT";
			} else if (entity instanceof Player _plr1 && _plr1.containerMenu instanceof FurnaceCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "F";
			} else if (entity instanceof Player _plr2 && _plr2.containerMenu instanceof CraftingTableRemovingCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "CTR";
			} else if (entity instanceof Player _plr3 && _plr3.containerMenu instanceof FurnaceRemovingCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "FR";
			} else if (entity instanceof Player _plr4 && _plr4.containerMenu instanceof BlastFurnaceCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "BF";
			} else if (entity instanceof Player _plr5 && _plr5.containerMenu instanceof BlastFurnaceRemovingCTGUIMenu) {
				RecipeGeneratorModVariables.openedGUI = "BFR";
			}
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				NetworkHooks.openScreen((ServerPlayer) _ent, new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("ChoosingTheRecipeGenerationMethodGUI");
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new ChoosingTheRecipeGenerationMethodGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		}
	}
}
