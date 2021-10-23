package dev.psygamer.wireframe.block.entity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.internal.block.entity.InternalBlockEntity;
import dev.psygamer.wireframe.registry.BlockEntityRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.world.World;

import java.util.function.Supplier;

public class BlockEntity {
	
	private final Identifier identifier;
	private final Block[] tileEntityHolders;
	
	private final net.minecraft.tileentity.TileEntity internal;
	
	private World world;
	private BlockPosition position;
	
	private BlockEntity(final net.minecraft.tileentity.TileEntity internal) {
		this.identifier = Identifier.get(internal.getType().getRegistryName());
		this.tileEntityHolders = new Block[0];
		
		this.internal = internal;
	}
	
	public BlockEntity(final Identifier identifier) {
		final BlockEntity.Definition definition = BlockEntityRegistry.getBlockEntityDefinition(identifier);
		
		this.identifier = identifier;
		this.tileEntityHolders = definition.getBlockEntityHolders();
		
		this.internal = new InternalBlockEntity(this);
	}
	
	public static BlockEntity get(final net.minecraft.tileentity.TileEntity internal) {
		if (internal == null)
			return null;
		
		if (internal instanceof InternalBlockEntity)
			return ((InternalBlockEntity) internal).getBlockEntity();
		
		return new BlockEntity(internal);
	}
	
	public void saveNBT(final TagCompound tagCompound) {
	}
	
	public void loadNBT(final TagCompound tagCompound) {
	}
	
	public void getClientSyncTag(final TagCompound tagCompound) {
		saveNBT(tagCompound);
	}
	
	public void handleClientSyncTag(final TagCompound tagCompound) {
		loadNBT(tagCompound);
	}
	
	public final void setWorldAndPosition(final World world, final BlockPosition position) {
		this.world = world;
		this.position = position;
	}
	
	public void markChanged() {
		this.internal.setChanged();
	}
	
	public final Identifier getIdentifier() {
		return this.identifier;
	}
	
	public final Block[] getTileEntityHolders() {
		return this.tileEntityHolders;
	}
	
	public final World getWorld() {
		return this.world;
	}
	
	public final BlockPosition getPosition() {
		return this.position;
	}
	
	public final net.minecraft.tileentity.TileEntity getInternal() {
		return this.internal;
	}
	
	public static final class Definition {
		
		private final Identifier identifier;
		
		private final Block[] blockEntityHolders;
		private final Supplier<BlockEntity> blockEntitySupplier;
		
		public Definition(final Identifier identifier,
						  final Supplier<BlockEntity> blockEntitySupplier,
						  final Block[] blockEntityHolders
		) {
			this.identifier = identifier;
			this.blockEntitySupplier = blockEntitySupplier;
			this.blockEntityHolders = blockEntityHolders;
		}
		
		public Identifier getIdentifier() {
			return this.identifier;
		}
		
		public Block[] getBlockEntityHolders() {
			return this.blockEntityHolders;
		}
		
		public Supplier<BlockEntity> getBlockEntitySupplier() {
			return this.blockEntitySupplier;
		}
	}
}
