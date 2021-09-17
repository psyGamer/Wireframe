package dev.psygamer.wireframe.tileentity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.util.Identifier;

public class TickingTileEntity extends TileEntity {
	
	protected TickingTileEntity(final Identifier identifier, final Block... tileEntityHolders) {
		super(identifier, tileEntityHolders);
	}
	
	public void tick() {
	
	}
}
