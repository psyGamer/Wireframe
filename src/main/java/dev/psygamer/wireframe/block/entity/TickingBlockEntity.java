package dev.psygamer.wireframe.block.entity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.util.Identifier;

public class TickingBlockEntity extends BlockEntity {
	
	protected TickingBlockEntity(final Identifier identifier, final Block... tileEntityHolders) {
		super(identifier, tileEntityHolders);
	}
	
	public void tick() {
	
	}
}