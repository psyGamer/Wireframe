package dev.psygamer.wireframe.internal.block.entity;

import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.internal.registry.InternalBlockEntityRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.BlockPositionKt;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.util.TagCompoundKt;
import dev.psygamer.wireframe.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class InternalBlockEntity extends TileEntity {
	
	protected final BlockEntity blockEntity;
	
	public InternalBlockEntity(final BlockEntity blockEntity) {
		super(
				InternalBlockEntityRegistry.getTileEntityType(blockEntity.getIdentifier())
		);
		
		this.blockEntity = blockEntity;
		this.blockEntity.setWorldAndPosition(
				World.get(getLevel()),
				BlockPositionKt.getWfWrapped(getBlockPos())
		);
	}
	
	public BlockEntity getBlockEntity() {
		return this.blockEntity;
	}
	
	@Override
	public void load(final BlockState blockState, final CompoundNBT compoundNBT) {
		super.load(blockState, compoundNBT);
		
		this.blockEntity.loadNBT(
				TagCompoundKt.getWfWrapped(compoundNBT)
		);
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT compoundNBT) {
		this.blockEntity.saveNBT(
				TagCompoundKt.getWfWrapped(compoundNBT)
		);
		
		return super.save(compoundNBT);
	}
	
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(getBlockPos(), -1, getUpdateTag());
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		final CompoundNBT updateTag = super.getUpdateTag();
		
		this.blockEntity.getClientSyncTag(
				TagCompoundKt.getWfWrapped(updateTag)
		);
		
		return updateTag;
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt) {
		handleUpdateTag(null, pkt.getTag());
	}
	
	@Override
	public void handleUpdateTag(final BlockState state, final CompoundNBT tag) {
		this.blockEntity.handleClientSyncTag(
				TagCompoundKt.getWfWrapped(tag)
		);
	}
}
