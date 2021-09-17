package dev.psygamer.wireframe.registry;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.internal.registry.InternalTileEntityRegistry;
import dev.psygamer.wireframe.tileentity.TileEntity;
import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;

public class TileEntityRegistry {
	
	protected static FreezableList<TileEntity> tileEntities = new FreezableArrayList<>();
	
	protected final String modID;
	protected final InternalTileEntityRegistry internal;
	
	public TileEntityRegistry(final String modID) {
		this.modID = modID;
		
		this.internal = new InternalTileEntityRegistry(this);
	}
	
	public static void register(final TileEntity tileEntity) {
		tileEntities.add(tileEntity);
	}
	
	public static ImmutableList<TileEntity> getTileEntities() {
		return tileEntities.toImmutable();
	}
	
	public static void freeze() {
		tileEntities.freeze();
	}
	
	public String getModID() {
		return this.modID;
	}
	
	public InternalTileEntityRegistry getInternal() {
		return this.internal;
	}
}
