package dev.psygamer.wireframe.internal.block.entity;

import dev.psygamer.wireframe.block.entity.TickingBlockEntity;
import net.minecraft.tileentity.ITickableTileEntity;

public class InternalTickingBlockEntity extends InternalBlockEntity implements ITickableTileEntity {
	
	public InternalTickingBlockEntity(final TickingBlockEntity tileEntity) {
		super(tileEntity);
	}
	
	@Override
	public void tick() {
		((TickingBlockEntity) this.blockEntity).tick();
	}
}
