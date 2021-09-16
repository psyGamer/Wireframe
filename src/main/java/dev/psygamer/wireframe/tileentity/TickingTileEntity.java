package dev.psygamer.wireframe.tileentity;

import dev.psygamer.wireframe.block.BlockFoundation;
import dev.psygamer.wireframe.util.Identifier;

public class TickingTileEntity extends TileEntityFoundation {
	
	protected TickingTileEntity(final Identifier identifier, final BlockFoundation... tileEntityHolders) {
		super(identifier, tileEntityHolders);
	}
	
	public void tick() {
	
	}
}
