package dev.psygamer.wireframe.internal.block.entity;

import dev.psygamer.wireframe.block.entity.TickingBlockEntity;
import net.minecraft.tileentity.ITickableTileEntity;

import java.util.function.Supplier;

public class InternalTickingBlockEntity extends InternalBlockEntity implements ITickableTileEntity {
	
	public InternalTickingBlockEntity(final TickingBlockEntity tileEntity,
									  final Supplier<TickingBlockEntity> newInstanceSupplier
	) {
		super(tileEntity, newInstanceSupplier);
	}
	
	@Override
	public void tick() {
		((TickingBlockEntity) this.tileEntity).tick();
	}
}
