package dev.psygamer.wireframe.api.block.entity

import dev.psygamer.wireframe.api.block.Block
import dev.psygamer.wireframe.api.world.World
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.*

open class BlockEntity {

	class Definition(
		val identifier: Identifier,

		val blockEntitySupplier: () -> BlockEntity,
		val blockEntityHolders: Array<Block>,

		val blockEntityClass: Class<out BlockEntity>,
	)

	val identifier: Identifier
	val mcNative: net.minecraft.tileentity.TileEntity

	val world: World
		get() = this.mcNative.level!!.wfWrapped
	val position: BlockPosition
		get() = this.mcNative.blockPos.wfWrapped

	internal constructor(mcNative: net.minecraft.tileentity.TileEntity) {
		this.identifier = mcNative.type.registryName?.wfWrapped
						  ?: throw IllegalArgumentException("Tried to wrap block entity without registry name!")
		this.mcNative = mcNative
	}

	protected constructor(identifier: Identifier) {
		this.identifier = identifier
		this.mcNative = NativeBlockEntity(this)
	}

	open fun saveNBT(tagCompound: TagCompound) {}
	open fun loadNBT(tagCompound: TagCompound) {}

	open fun getClientSyncTag(tagCompound: TagCompound) {
		saveNBT(tagCompound)
	}

	open fun handleClientSyncTag(tagCompound: TagCompound) {
		loadNBT(tagCompound)
	}

	fun markChanged() {
		mcNative.setChanged()
	}
}
