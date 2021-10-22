package dev.psygamer.wireframe.block.entity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.util.Identifier;

import java.util.function.Supplier;

public class TickingBlockEntity extends BlockEntity {
	
	protected TickingBlockEntity(final Identifier identifier, final Supplier<TickingBlockEntity> newInstanceSupplier,
								 final Block... tileEntityHolders
	) {
		super(identifier, newInstanceSupplier, tileEntityHolders);
	}
	
	public void tick() {
	
	}
}
