package net.mcreator.recipe_generator.network;

import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import net.mcreator.recipe_generator.RecipeGeneratorMod;

import java.util.function.Supplier;
import java.util.ArrayList;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeGeneratorModVariables {
	public static String item_in_slot_0 = "";
	public static String item_in_slot_1 = "";
	public static String item_in_slot_2 = "";
	public static String item_in_slot_3 = "";
	public static String item_in_slot_4 = "";
	public static String item_in_slot_5 = "";
	public static String item_in_slot_6 = "";
	public static String item_in_slot_7 = "";
	public static String item_in_slot_8 = "";
	public static String item_in_slot_9 = "";
	public static String Generated_recipe = "\"\"";
	public static String Recipe_name = "\"\"";
	public static boolean Is_mirrored = false;
	public static boolean Is_shapeless = false;
	public static String Mirror_axis = "NONE";
	public static double filesWithGeneratedNameCount = 0;
	public static String openedGUI = "\"\"";
	public static boolean methodSelected = false;
	public static double item_in_slot_9_count = 0;
	public static String item_in_slot_10 = "";
	public static String item_in_slot_11 = "";
	public static String item_in_slot_12 = "";
	public static String item_in_slot_13 = "";
	public static String item_in_slot_14 = "\"\"";
	public static String item_in_slot_15 = "\"\"";
	public static String item_in_slot_16 = "\"\"";
	public static String item_in_slot_18 = "\"\"";
	public static String item_in_slot_19 = "\"\"";
	public static String item_in_slot_20 = "\"\"";
	public static String item_in_slot_21 = "\"\"";
	public static String item_in_slot_22 = "\"\"";
	public static String item_in_slot_23 = "\"\"";
	public static String item_in_slot_24 = "\"\"";
	public static String item_in_slot_25 = "\"\"";
	public static String item_in_slot_26 = "\"\"";
	public static String item_in_slot_27 = "\"\"";
	public static String item_in_slot_28 = "\"\"";
	public static String item_in_slot_29 = "\"\"";
	public static String item_in_slot_30 = "\"\"";
	public static String item_in_slot_31 = "\"\"";
	public static String item_in_slot_32 = "\"\"";
	public static String item_in_slot_33 = "\"\"";
	public static String item_in_slot_34 = "\"\"";
	public static String item_in_slot_35 = "\"\"";
	public static String item_in_slot_36 = "\"\"";
	public static String item_in_slot_37 = "\"\"";
	public static String item_in_slot_38 = "\"\"";
	public static String item_in_slot_39 = "\"\"";
	public static String item_in_slot_40 = "\"\"";
	public static String item_in_slot_41 = "\"\"";
	public static String item_in_slot_42 = "\"\"";
	public static String item_in_slot_43 = "\"\"";
	public static String item_in_slot_44 = "\"\"";
	public static String item_in_slot_45 = "\"\"";
	public static String item_in_slot_46 = "\"\"";
	public static String item_in_slot_47 = "\"\"";
	public static String item_in_slot_48 = "\"\"";
	public static String item_in_slot_49 = "\"\"";
	public static String item_in_slot_50 = "\"\"";
	public static String item_in_slot_51 = "\"\"";
	public static String item_in_slot_52 = "\"\"";
	public static String item_in_slot_53 = "\"\"";
	public static String item_in_slot_54 = "\"\"";
	public static String item_in_slot_55 = "\"\"";
	public static String item_in_slot_56 = "\"\"";
	public static String item_in_slot_57 = "\"\"";
	public static String item_in_slot_58 = "\"\"";
	public static String item_in_slot_59 = "\"\"";
	public static String item_in_slot_60 = "\"\"";
	public static String item_in_slot_61 = "\"\"";
	public static String item_in_slot_62 = "\"\"";
	public static String item_in_slot_63 = "\"\"";
	public static String item_in_slot_64 = "\"\"";
	public static String item_in_slot_65 = "\"\"";
	public static String item_in_slot_66 = "\"\"";
	public static String item_in_slot_67 = "\"\"";
	public static String item_in_slot_68 = "\"\"";
	public static String item_in_slot_69 = "\"\"";
	public static String item_in_slot_70 = "\"\"";
	public static String item_in_slot_71 = "\"\"";
	public static String item_in_slot_72 = "\"\"";
	public static String item_in_slot_73 = "\"\"";
	public static String item_in_slot_74 = "\"\"";
	public static String item_in_slot_75 = "\"\"";
	public static String item_in_slot_76 = "\"\"";
	public static String item_in_slot_77 = "\"\"";
	public static String item_in_slot_78 = "\"\"";
	public static String item_in_slot_79 = "\"\"";
	public static String item_in_slot_80 = "\"\"";
	public static String item_in_slot_81 = "\"\"";
	public static String item_in_slot_82 = "\"\"";

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::buffer, SavedDataSyncMessage::new, SavedDataSyncMessage::handler);
		RecipeGeneratorMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	@SubscribeEvent
	public static void init(RegisterCapabilitiesEvent event) {
		event.register(PlayerVariables.class);
	}

	@Mod.EventBusSubscriber
	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			event.getOriginal().revive();
			PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
			clone.preGeneratedRecipe = original.preGeneratedRecipe;
			if (!event.isWasDeath()) {
			}
			if (!event.getEntity().level().isClientSide()) {
				for (Entity entityiterator : new ArrayList<>(event.getEntity().level().players())) {
					((PlayerVariables) entityiterator.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entityiterator);
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = MapVariables.get(event.getEntity().level());
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (mapdata != null)
					RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(0, mapdata));
				if (worlddata != null)
					RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = WorldVariables.get(event.getEntity().level());
				if (worlddata != null)
					RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.getEntity()), new SavedDataSyncMessage(1, worlddata));
			}
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "recipe_generator_worldvars";
		public String selectedMethod = "NONE";

		public static WorldVariables load(CompoundTag tag) {
			WorldVariables data = new WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			selectedMethod = nbt.getString("selectedMethod");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putString("selectedMethod", selectedMethod);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide())
				RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(level::dimension), new SavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(LevelAccessor world) {
			if (world instanceof ServerLevel level) {
				return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "recipe_generator_mapvars";
		public String item_in_slot_17 = "\"\"";

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			if (nbt == null) {
				nbt = save(new CompoundTag());
			}
			item_in_slot_17 = nbt.getString("item_in_slot_17");
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			nbt.putString("item_in_slot_17", item_in_slot_17);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level && !world.isClientSide())
				RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new SavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		private final int type;
		private SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			CompoundTag nbt = buffer.readNbt();
			if (nbt != null) {
				this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
				if (this.data instanceof MapVariables mapVariables)
					mapVariables.read(nbt);
				else if (this.data instanceof WorldVariables worldVariables)
					worldVariables.read(nbt);
			}
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			if (message.data != null)
				buffer.writeNbt(message.data.save(new CompoundTag()));
		}

		public static void handler(SavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer() && message.data != null) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
	});

	@Mod.EventBusSubscriber
	private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
		@SubscribeEvent
		public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
			if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
				event.addCapability(new ResourceLocation("recipe_generator", "player_variables"), new PlayerVariablesProvider());
		}

		private final PlayerVariables playerVariables = new PlayerVariables();
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public Tag serializeNBT() {
			return playerVariables.writeNBT();
		}

		@Override
		public void deserializeNBT(Tag nbt) {
			playerVariables.readNBT(nbt);
		}
	}

	public static class PlayerVariables {
		public String preGeneratedRecipe = "\"\"";

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer)
				RecipeGeneratorMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(entity.level()::dimension), new PlayerVariablesSyncMessage(this, entity.getId()));
		}

		public Tag writeNBT() {
			CompoundTag nbt = new CompoundTag();
			nbt.putString("preGeneratedRecipe", preGeneratedRecipe);
			return nbt;
		}

		public void readNBT(Tag tag) {
			if (tag == null) {
				tag = writeNBT();
			}
			CompoundTag nbt = (CompoundTag) tag;
			if (nbt == null) {
				nbt = (CompoundTag) writeNBT();
			}
			preGeneratedRecipe = nbt.getString("preGeneratedRecipe");
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		RecipeGeneratorMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
	}

	public static class PlayerVariablesSyncMessage {
		private final int target;
		private final PlayerVariables data;

		public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
			this.data = new PlayerVariables();
			this.data.readNBT(buffer.readNbt());
			this.target = buffer.readInt();
		}

		public PlayerVariablesSyncMessage(PlayerVariables data, int entityid) {
			this.data = data;
			this.target = entityid;
		}

		public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeNbt((CompoundTag) message.data.writeNBT());
			buffer.writeInt(message.target);
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.level().getEntity(message.target).getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
					variables.preGeneratedRecipe = message.data.preGeneratedRecipe;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
