package dev.psygamer.wireframe.impl.v16.data.client;

import dev.psygamer.wireframe.core.implementation.ImplementationVersion;
import dev.psygamer.wireframe.core.implementation.MinecraftVersion;
import dev.psygamer.wireframe.lib.block.BlockProperty;
import dev.psygamer.wireframe.lib.block.BlockWrapper;
import dev.psygamer.wireframe.lib.data.client.BlockStateDataProvider;
import dev.psygamer.wireframe.lib.registry.BlockRegistry;

import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

@ImplementationVersion(MinecraftVersion.v16)
public class BlockStateDataProviderImpl16 extends BlockStateDataProvider {
	
	public BlockStateDataProviderImpl16(final DataGenerator gen, final String modid, final ExistingFileHelper exFileHelper) {
		super(gen, modid, exFileHelper);
	}
	
	@Override
	protected void registerStatesAndModels() {
		for (final BlockWrapper blockWrapper : BlockRegistry.getBlockWrappers()) {
			if (blockWrapper.getBlockProperties().size() == 0) {
				simpleBlock(blockWrapper.getBlock());
			} else {
				for (final BlockState state : blockWrapper.getBlock().getStateDefinition().getPossibleStates()) {
					ConfiguredModel.Builder<?> modelBuilder = ConfiguredModel.builder();
					
					for (final BlockProperty<? extends Comparable<?>> property : blockWrapper.getBlockProperties()) {
						modelBuilder = property.applyBlockModelModification(
								state.getValue(property),
								modelBuilder
						);
					}
				}
			}
		}
	}
}
