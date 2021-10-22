package dev.psygamer.wireframe.internal.block.entity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.world.World;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;
import java.util.Arrays;

public class InternalBlockEntity extends TileEntity {
	
	protected final BlockEntity tileEntity;
	
	public InternalBlockEntity(final BlockEntity tileEntity) {
		super(generateTileEntityType(tileEntity));
		
		this.tileEntity = tileEntity;
		this.tileEntity.setWorldAndPosition(
				World.get(getLevel()),
				BlockPosition.get(getBlockPos())
		);
	}
	
	private static TileEntityType<?> generateTileEntityType(final BlockEntity tileEntity) {
		return TileEntityType.Builder
				.of(() -> BlockEntity.getNewInstanceSupplier(tileEntity.getClass())
									 .get().getInternal(),
				
					Arrays.stream(tileEntity.getTileEntityHolders())
						  .map(Block::getInternal)
						  .toArray(net.minecraft.block.Block[]::new)
				)
				.build(null)
				.setRegistryName(
						tileEntity.getIdentifier().getNamespace(),
						tileEntity.getIdentifier().getPath()
				);
	}
	
	@Override
	public void load(final BlockState blockState, final CompoundNBT compoundNBT) {
		super.load(blockState, compoundNBT);
		
		this.tileEntity.loadNBT(
				TagCompound.get(compoundNBT)
		);
	}
	
	@Override
	public CompoundNBT save(final CompoundNBT compoundNBT) {
		this.tileEntity.saveNBT(
				TagCompound.get(compoundNBT)
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
		
		this.tileEntity.getClientSyncTag(
				TagCompound.get(updateTag)
		);
		
		return updateTag;
	}
	
	@Override
	public void onDataPacket(final NetworkManager net, final SUpdateTileEntityPacket pkt) {
		handleUpdateTag(null, pkt.getTag());
	}
	
	@Override
	public void handleUpdateTag(final BlockState state, final CompoundNBT tag) {
		this.tileEntity.handleClientSyncTag(
				TagCompound.get(tag)
		);
	}
}
