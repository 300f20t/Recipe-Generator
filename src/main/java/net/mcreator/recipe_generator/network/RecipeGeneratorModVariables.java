package net.mcreator.recipe_generator.network;

import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.CompoundTag;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;

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

	public static void SyncJoin() {
		ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
			if (entity instanceof Player) {
				if (!world.isClientSide()) {
					SavedData mapdata = MapVariables.get(world);
					SavedData worlddata = WorldVariables.get(world);
				}
			}
		});
	}

	public static void SyncChangeWorld() {
		ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
			if (!destination.isClientSide()) {
				SavedData worlddata = WorldVariables.get(destination);
			}
		});
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

		public static MapVariables load(CompoundTag tag) {
			MapVariables data = new MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(LevelAccessor world) {
			if (world instanceof ServerLevelAccessor serverLevelAcc) {
				return serverLevelAcc.level().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class SavedDataSyncMessage {
		public int type;
		public SavedData data;

		public SavedDataSyncMessage(FriendlyByteBuf buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			if (this.data instanceof MapVariables _mapvars)
				_mapvars.read(buffer.readNbt());
			else if (this.data instanceof WorldVariables _worldvars)
				_worldvars.read(buffer.readNbt());
		}

		public SavedDataSyncMessage(int type, SavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
			buffer.writeInt(message.type);
			buffer.writeNbt(message.data.save(new CompoundTag()));
		}
	}
}
