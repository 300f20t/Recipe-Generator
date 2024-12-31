
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.recipe_generator.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.registries.Registries;

import net.mcreator.recipe_generator.world.inventory.FurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.FurnaceCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingtableCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.CraftingTableRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGenerationMethodGUIMenu;
import net.mcreator.recipe_generator.world.inventory.ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceRemovingCTGUIMenu;
import net.mcreator.recipe_generator.world.inventory.BlastFurnaceCTGUIMenu;
import net.mcreator.recipe_generator.RecipeGeneratorMod;

import javax.annotation.Nullable;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, RecipeGeneratorMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingtableCTGUIMenu>> CRAFTINGTABLE_CTGUI = REGISTRY.register("craftingtable_ctgui", () -> IMenuTypeExtension.create(CraftingtableCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceCTGUIMenu>> FURNACE_CTGUI = REGISTRY.register("furnace_ctgui", () -> IMenuTypeExtension.create(FurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<CraftingTableRemovingCTGUIMenu>> CRAFTING_TABLE_REMOVING_CTGUI = REGISTRY.register("crafting_table_removing_ctgui", () -> IMenuTypeExtension.create(CraftingTableRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<FurnaceRemovingCTGUIMenu>> FURNACE_REMOVING_CTGUI = REGISTRY.register("furnace_removing_ctgui", () -> IMenuTypeExtension.create(FurnaceRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlastFurnaceCTGUIMenu>> BLAST_FURNACE_CTGUI = REGISTRY.register("blast_furnace_ctgui", () -> IMenuTypeExtension.create(BlastFurnaceCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BlastFurnaceRemovingCTGUIMenu>> BLAST_FURNACE_REMOVING_CTGUI = REGISTRY.register("blast_furnace_removing_ctgui", () -> IMenuTypeExtension.create(BlastFurnaceRemovingCTGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ChoosingTheRecipeGenerationMethodGUIMenu>> CHOOSING_THE_RECIPE_GENERATION_METHOD_GUI = REGISTRY.register("choosing_the_recipe_generation_method_gui",
			() -> IMenuTypeExtension.create(ChoosingTheRecipeGenerationMethodGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu>> CHOOSING_THE_RECIPE_GENERATING_METHOD_GUI_WITH_COMMAND = REGISTRY.register("choosing_the_recipe_generating_method_gui_with_command",
			() -> IMenuTypeExtension.create(ChoosingTheRecipeGeneratingMethodGUIWithCommandMenu::new));

	public static void setText(String boxname, String value, @Nullable ServerPlayer player) {
		if (player != null) {
			PacketDistributor.sendToPlayer(player, new GuiSyncMessage(boxname, value));
		} else {
			PacketDistributor.sendToAllPlayers(new GuiSyncMessage(boxname, value));
		}
	}

	public static record GuiSyncMessage(String editbox, String value) implements CustomPacketPayload {
		public static final Type<GuiSyncMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(RecipeGeneratorMod.MODID, "gui_sync"));
		public static final StreamCodec<RegistryFriendlyByteBuf, GuiSyncMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, GuiSyncMessage message) -> {
			ComponentSerialization.TRUSTED_STREAM_CODEC.encode(buffer, Component.literal(message.editbox));
			ComponentSerialization.TRUSTED_STREAM_CODEC.encode(buffer, Component.literal(message.value));
		}, (RegistryFriendlyByteBuf buffer) -> {
			String editbox = ComponentSerialization.TRUSTED_STREAM_CODEC.decode(buffer).getString();
			String value = ComponentSerialization.TRUSTED_STREAM_CODEC.decode(buffer).getString();
			return new GuiSyncMessage(editbox, value);
		});

		@Override
		public Type<GuiSyncMessage> type() {
			return TYPE;
		}

		public static void handleData(final GuiSyncMessage message, final IPayloadContext context) {
			if (context.flow() == PacketFlow.CLIENTBOUND) {
				context.enqueueWork(() -> {
					RecipeGeneratorModScreens.handleTextBoxMessage(message);
				}).exceptionally(e -> {
					context.connection().disconnect(Component.literal(e.getMessage()));
					return null;
				});
			}
		}
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(GuiSyncMessage.TYPE, GuiSyncMessage.STREAM_CODEC, GuiSyncMessage::handleData);
	}
}
