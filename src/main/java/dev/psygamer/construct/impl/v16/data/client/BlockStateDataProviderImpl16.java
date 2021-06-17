package dev.psygamer.construct.impl.v16.data.client;

import dev.psygamer.construct.lib.block.BlockProperty;
import dev.psygamer.construct.lib.block.BlockWrapper;
import dev.psygamer.construct.lib.data.client.BlockStateDataProvider;
import dev.psygamer.construct.lib.registry.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.Property;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
