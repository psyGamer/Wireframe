package dev.psygamer.wireframe.impl.v16.data.client;

import dev.psygamer.wireframe.core.impl.MinecraftVersion;
import dev.psygamer.wireframe.core.impl.ImplementationVersion;

import dev.psygamer.wireframe.api.data.client.BlockStateDataProvider;
import dev.psygamer.wireframe.api.registry.BlockRegistry;

import net.minecraftforge.common.data.ExistingFileHelper;

import net.minecraft.data.DataGenerator;


@ImplementationVersion(MinecraftVersion.v16)
public class BlockStateDataProviderImpl16 extends BlockStateDataProvider {
	
	public BlockStateDataProviderImpl16(final DataGenerator gen, final String modid, final ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
	}
	
	@Override
	protected void registerStatesAndModels() {
//		for (final BlockWrapper blockWrapper : BlockRegistry.getBlockWrappers()) {
////			simpleBlock(blockWrapper.getBlock());
//
////			if (blockWrapper.getBlockProperties().size() == 0) {
////				simpleBlock(blockWrapper.getBlock());
////			} else {
////				for (final BlockState state : blockWrapper.getBlock().getStateDefinition().getPossibleStates()) {
////					ConfiguredModel.Builder<?> modelBuilder = ConfiguredModel.builder();
////
////					for (final BlockProperty<? extends Comparable<?>> property : blockWrapper.getBlockProperties()) {
////						modelBuilder = property.applyBlockModelModification(
////								state.getValue(property),
////								modelBuilder
////						);
////					}
////				}
////			}
//		}
	}
}
