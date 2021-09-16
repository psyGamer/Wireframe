package dev.psygamer.wireframe.internal.tileentity;

import dev.psygamer.wireframe.tileentity.TileEntityFoundation;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class InternalTileEntity extends TileEntity {
	
	protected final TileEntityFoundation tileEntity;
	
	public InternalTileEntity(final TileEntityFoundation tileEntity) {
		super(null);
		
		this.tileEntity = tileEntity;
		this.tileEntity.setWorldAndPosition(
				World.get(getLevel()),
				BlockPosition.get(getBlockPos())
		);
	}
	
	public void markChanged() {
		setChanged();
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT compoundNBT) {
		this.tileEntity.saveNBT(
				TagCompound.get(compoundNBT)
		);
		
		return super.save(compoundNBT);
	}
	
	@Override
	public void load(final BlockState blockState, final CompoundNBT compoundNBT) {
		super.load(blockState, compoundNBT);
		
		this.tileEntity.loadNBT(
				TagCompound.get(compoundNBT)
		);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		final CompoundNBT updateTag = super.getUpdateTag();
		
		this.tileEntity.getClientSyncTag(
				TagCompound.get(updateTag)
		);
		
		return updateTag;
	}
	
	@Override
	public void handleUpdateTag(final BlockState state, final CompoundNBT tag) {
		this.tileEntity.handleClientSyncTag(
				TagCompound.get(tag)
		);
	}
	
	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(getBlockPos(), -1, getUpdateTag());
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt) {
		handleUpdateTag(null, pkt.getTag());
	}
}
