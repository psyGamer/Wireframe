package dev.psygamer.wireframe.tileentity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.internal.tileentity.InternalTileEntity;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.world.World;

public class TileEntity {
	
	private final Identifier identifier;
	private final Block[] tileEntityHolders;
	
	private final InternalTileEntity internal;
	
	private World world;
	private BlockPosition position;
	
	protected TileEntity(final Identifier identifier, final Block... tileEntityHolders) {
		
		this.identifier = identifier;
		this.tileEntityHolders = tileEntityHolders;
		
		this.internal = new InternalTileEntity(this);
	}
	
	public void saveNBT(final TagCompound tagCompound) {
	}
	
	public void loadNBT(final TagCompound tagCompound) {
	}
	
	public void getClientSyncTag(final TagCompound tagCompound) {
		saveNBT(tagCompound);
	}
	
	public void handleClientSyncTag(final TagCompound tagCompound) {
		loadNBT(tagCompound);
	}
	
	public final void setWorldAndPosition(final World world, final BlockPosition position) {
		this.world = world;
		this.position = position;
	}
	
	public void markChanged() {
		this.internal.markChanged();
	}
	
	public final Identifier getIdentifier() {
		return this.identifier;
	}
	
	public final Block[] getTileEntityHolders() {
		return this.tileEntityHolders;
	}
	
	public final World getWorld() {
		return this.world;
	}
	
	public final BlockPosition getPosition() {
		return this.position;
	}
	
	public final InternalTileEntity getInternal() {
		return this.internal;
	}
}
