package net.mcreator.recipe_generator.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.recipe_generator.network.RecipeGeneratorModVariables;

import java.util.HashMap;

public class GenerateAvaritaCraftingTableRecipeProcedure {
	public static void execute(LevelAccessor world, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		{
			String _setval = RecipeGeneratorModVariables.item_in_slot_81 + " * " + new java.text.DecimalFormat("##").format(RecipeGeneratorModVariables.item_in_slot_9_count) + ", ["
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_0 + ", " + RecipeGeneratorModVariables.item_in_slot_1 + ", " + RecipeGeneratorModVariables.item_in_slot_2 + ", " + RecipeGeneratorModVariables.item_in_slot_3 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_4 + ", " + RecipeGeneratorModVariables.item_in_slot_5 + ", " + RecipeGeneratorModVariables.item_in_slot_6 + ", " + RecipeGeneratorModVariables.item_in_slot_7 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_8 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_9 + ", " + RecipeGeneratorModVariables.item_in_slot_10 + ", " + RecipeGeneratorModVariables.item_in_slot_11 + ", " + RecipeGeneratorModVariables.item_in_slot_12 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_13 + ", " + RecipeGeneratorModVariables.item_in_slot_14 + ", " + RecipeGeneratorModVariables.item_in_slot_15 + ", " + RecipeGeneratorModVariables.item_in_slot_16 + ", "
							+ RecipeGeneratorModVariables.MapVariables.get(world).item_in_slot_17 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_18 + ", " + RecipeGeneratorModVariables.item_in_slot_19 + ", " + RecipeGeneratorModVariables.item_in_slot_20 + ", " + RecipeGeneratorModVariables.item_in_slot_21 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_22 + ", " + RecipeGeneratorModVariables.item_in_slot_23 + ", " + RecipeGeneratorModVariables.item_in_slot_24 + ", " + RecipeGeneratorModVariables.item_in_slot_25 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_26 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_27 + ", " + RecipeGeneratorModVariables.item_in_slot_28 + ", " + RecipeGeneratorModVariables.item_in_slot_29 + ", " + RecipeGeneratorModVariables.item_in_slot_30 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_31 + ", " + RecipeGeneratorModVariables.item_in_slot_32 + ", " + RecipeGeneratorModVariables.item_in_slot_33 + ", " + RecipeGeneratorModVariables.item_in_slot_34 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_35 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_36 + ", " + RecipeGeneratorModVariables.item_in_slot_37 + ", " + RecipeGeneratorModVariables.item_in_slot_38 + ", " + RecipeGeneratorModVariables.item_in_slot_39 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_40 + ", " + RecipeGeneratorModVariables.item_in_slot_41 + ", " + RecipeGeneratorModVariables.item_in_slot_42 + ", " + RecipeGeneratorModVariables.item_in_slot_43 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_44 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_45 + ", " + RecipeGeneratorModVariables.item_in_slot_46 + ", " + RecipeGeneratorModVariables.item_in_slot_47 + ", " + RecipeGeneratorModVariables.item_in_slot_48 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_49 + ", " + RecipeGeneratorModVariables.item_in_slot_50 + ", " + RecipeGeneratorModVariables.item_in_slot_51 + ", " + RecipeGeneratorModVariables.item_in_slot_52 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_53 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_54 + ", " + RecipeGeneratorModVariables.item_in_slot_55 + ", " + RecipeGeneratorModVariables.item_in_slot_56 + ", " + RecipeGeneratorModVariables.item_in_slot_57 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_58 + ", " + RecipeGeneratorModVariables.item_in_slot_59 + ", " + RecipeGeneratorModVariables.item_in_slot_60 + ", " + RecipeGeneratorModVariables.item_in_slot_61 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_62 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_63 + ", " + RecipeGeneratorModVariables.item_in_slot_64 + ", " + RecipeGeneratorModVariables.item_in_slot_65 + ", " + RecipeGeneratorModVariables.item_in_slot_66 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_67 + ", " + RecipeGeneratorModVariables.item_in_slot_68 + ", " + RecipeGeneratorModVariables.item_in_slot_69 + ", " + RecipeGeneratorModVariables.item_in_slot_70 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_71 + "], ")
					+ ("\n" + "[" + RecipeGeneratorModVariables.item_in_slot_72 + ", " + RecipeGeneratorModVariables.item_in_slot_73 + ", " + RecipeGeneratorModVariables.item_in_slot_74 + ", " + RecipeGeneratorModVariables.item_in_slot_75 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_76 + ", " + RecipeGeneratorModVariables.item_in_slot_77 + ", " + RecipeGeneratorModVariables.item_in_slot_78 + ", " + RecipeGeneratorModVariables.item_in_slot_79 + ", "
							+ RecipeGeneratorModVariables.item_in_slot_80 + "], ")
					+ "\n" + "]);";
			entity.getCapability(RecipeGeneratorModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.preGeneratedRecipe = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		AvaritaCraftingTableAddShapedCraftTweakerProcedure.execute(entity, guistate);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(RecipeGeneratorModVariables.Generated_recipe), false);
	}
}
