package dev.psygamer.wireframe.block.entity;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.internal.block.entity.InternalBlockEntity;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockEntity {
	
	private static final Map<Class<? extends BlockEntity>, Supplier<? extends BlockEntity>>
			newInstanceSupplierCache = new HashMap<>();
	
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
	
	protected BlockEntity(final Supplier<? extends BlockEntity> newInstanceSupplier, final Block... tileEntityHolders) {
		this(tileEntityHolders[0].getIdentifier(), newInstanceSupplier, tileEntityHolders);
	}
	
	protected BlockEntity(final Identifier identifier, final Supplier<? extends BlockEntity> newInstanceSupplier,
						  final Block... tileEntityHolders
	) {
		newInstanceSupplierCache.put(this.getClass(), newInstanceSupplier);
		
		this.identifier = identifier;
		this.tileEntityHolders = tileEntityHolders;
		
		this.internal = new InternalBlockEntity(this);
	}
	
	public static BlockEntity get(final net.minecraft.tileentity.TileEntity internal) {
		if (internal == null)
			return null;
		
		return new BlockEntity(internal);
	}
	
	public static Supplier<? extends BlockEntity> getNewInstanceSupplier(final Class<? extends BlockEntity> clazz) {
		if (newInstanceSupplierCache.containsValue(clazz))
			return newInstanceSupplierCache.get(clazz);
		
		return null;
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
