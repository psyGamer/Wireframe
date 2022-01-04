package dev.psygamer.wireframe.block.entity

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.internal.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.registry.BlockEntityRegistry
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.TagCompound
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.World

open class BlockEntity {
	
	class Definition(
		val identifier: Identifier,
		
		val blockEntitySupplier: () -> BlockEntity,
		val blockEntityHolders: Array<Block>
	)
	
	val identifier: Identifier
	val holders: Array<Block>
	
	val mcNative: net.minecraft.tileentity.TileEntity
	
	val world: World
		get() = World.get(this.mcNative.level)
	val position: BlockPosition
		get() = this.mcNative.blockPos.wfWrapped
	
	internal constructor(internal: net.minecraft.tileentity.TileEntity) {
		this.identifier = internal.type.registryName?.wfWrapped
						  ?: throw IllegalArgumentException("Tried to wrap native block without registry name!")
		
		this.holders = emptyArray()
		
		this.mcNative = internal
	}
	
	protected constructor(identifier: Identifier) {
		val definition = BlockEntityRegistry.getDefinition(identifier)
						 ?: throw IllegalStateException("Tried to create unregistered BlockEntity!")
		
		this.identifier = identifier
		this.holders = definition.blockEntityHolders
		
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
