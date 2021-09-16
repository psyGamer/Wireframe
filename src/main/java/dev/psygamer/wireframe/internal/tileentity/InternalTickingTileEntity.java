package dev.psygamer.wireframe.internal.tileentity;

import dev.psygamer.wireframe.tileentity.TickingTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;

public class InternalTickingTileEntity extends InternalTileEntity implements ITickableTileEntity {
	
	public InternalTickingTileEntity(final TickingTileEntity tileEntity) {
		super(tileEntity);
	}
	
	@Override
	public void tick() {
		((TickingTileEntity) this.tileEntity).tick();
	}
}
