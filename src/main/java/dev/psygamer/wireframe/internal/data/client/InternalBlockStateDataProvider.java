package dev.psygamer.wireframe.internal.data.client;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class InternalBlockStateDataProvider extends BlockStateProvider {
	
	public InternalBlockStateDataProvider(final DataGenerator gen, final String modid, final ExistingFileHelper exFileHelper) {
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
