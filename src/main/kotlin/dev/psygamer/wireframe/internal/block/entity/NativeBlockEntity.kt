package dev.psygamer.wireframe.internal.block.entity

import dev.psygamer.wireframe.block.entity.BlockEntity
import dev.psygamer.wireframe.internal.registry.NativeBlockEntityRegistry
import dev.psygamer.wireframe.wfWrapped
import net.minecraft.block.BlockState
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.TileEntity

open class NativeBlockEntity(open val blockEntity: BlockEntity) :
	TileEntity(NativeBlockEntityRegistry.getTileEntityType(blockEntity.identifier)) {
	
	override fun load(blockState: BlockState, compoundNBT: CompoundNBT) {
		super.load(blockState, compoundNBT)
		blockEntity.loadNBT(compoundNBT.wfWrapped)
	}
	
	override fun save(compoundNBT: CompoundNBT): CompoundNBT {
		blockEntity.saveNBT(compoundNBT.wfWrapped)
		return super.save(compoundNBT)
	}
	
	override fun getUpdatePacket(): SUpdateTileEntityPacket? {
		return SUpdateTileEntityPacket(blockPos, -1, updateTag)
	}
	
	override fun getUpdateTag(): CompoundNBT {
		val updateTag = super.getUpdateTag()
		blockEntity.getClientSyncTag(updateTag.wfWrapped)
		return updateTag
	}
	
	override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
		handleUpdateTag(null, pkt.tag)
	}
	
	override fun handleUpdateTag(state: BlockState?, tag: CompoundNBT) {
		blockEntity.handleClientSyncTag(tag.wfWrapped)
	}
}