package dev.psygamer.wireframe.nativeapi.block.entity

import net.minecraft.tileentity.ITickableTileEntity
import dev.psygamer.wireframe.api.block.entity.TickingBlockEntity

class NativeTickingBlockEntity(override val blockEntity: TickingBlockEntity) :
	NativeBlockEntity(blockEntity), ITickableTileEntity {

	override fun tick() {
		blockEntity.tick()
	}
}