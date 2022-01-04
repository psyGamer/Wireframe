package dev.psygamer.wireframe.internal.block.entity

import dev.psygamer.wireframe.block.entity.TickingBlockEntity
import net.minecraft.tileentity.ITickableTileEntity

class NativeTickingBlockEntity(override val blockEntity: TickingBlockEntity) :
	NativeBlockEntity(blockEntity), ITickableTileEntity {
	
	override fun tick() {
		blockEntity.tick()
	}
}