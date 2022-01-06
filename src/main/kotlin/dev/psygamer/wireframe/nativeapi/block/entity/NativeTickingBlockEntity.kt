package dev.psygamer.wireframe.nativeapi.block.entity

import dev.psygamer.wireframe.api.block.entity.TickingBlockEntity
import net.minecraft.tileentity.ITickableTileEntity

class NativeTickingBlockEntity(override val blockEntity: TickingBlockEntity) :
	NativeBlockEntity(blockEntity), ITickableTileEntity {
	
	override fun tick() {
		blockEntity.tick()
	}
}