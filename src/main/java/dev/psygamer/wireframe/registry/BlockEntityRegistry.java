package dev.psygamer.wireframe.registry;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.internal.registry.InternalBlockEntityRegistry;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

public class BlockEntityRegistry {
	
	protected static FreezableList<BlockEntity> tileEntities = new FreezableArrayList<>();
	
	protected final String modID;
	protected final InternalBlockEntityRegistry internal;
	
	public BlockEntityRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalBlockEntityRegistry(this);
	}
	
	public static void register(final BlockEntity tileEntity) {
		tileEntities.add(tileEntity);
	}
	
	public static ImmutableList<BlockEntity> getTileEntities() {
		return tileEntities.toImmutable();
	}
	
	public static void freeze() {
		tileEntities.freeze();
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalBlockEntityRegistry getInternal() {
		return this.internal;
	}
}
