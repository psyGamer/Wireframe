package dev.psyGamer.anvil.impl.v16.data.client;

import dev.psyGamer.anvil.lib.block.BlockProperty;
import dev.psyGamer.anvil.lib.block.BlockWrapper;
import dev.psyGamer.anvil.lib.data.client.BlockStateDataProvider;
import dev.psyGamer.anvil.lib.registry.BlockRegistry;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
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
				for (final VariantBlockStateBuilder.PartialBlockstate state : generateBlockStates(blockWrapper.getBlockProperties())) {
					ConfiguredModel.Builder<?> modelBuilder = ConfiguredModel.builder();
					
					for (final BlockProperty<? extends Comparable<?>> property : blockWrapper.getBlockProperties()) {
						modelBuilder = property.applyBlockModelModification(property.getValueClass().cast(state.getSetStates().get(property)), modelBuilder);
					}
				}
			}
		}
	}
	
	private List<VariantBlockStateBuilder.PartialBlockstate> generateBlockStates(
			final List<BlockProperty<?>> properties
	) {
		return generateBlockStates(new ArrayList<>(), properties);
	}
	
	private List<VariantBlockStateBuilder.PartialBlockstate> generateBlockStates(
			final List<VariantBlockStateBuilder.PartialBlockstate> blockStates,
			final List<BlockProperty<?>> properties
	) {
		final List<VariantBlockStateBuilder.PartialBlockstate> newBlockStates = new ArrayList<>();
		
		for (final VariantBlockStateBuilder.PartialBlockstate blockState : blockStates) {
			properties.get(0).getAllValues().forEach(pair -> newBlockStates.add(blockState.with(pair.getProperty(), pair.value())));
		}
		
		if (properties.size() <= 0) {
			return newBlockStates;
		}
		
		properties.remove(0);
		
		return generateBlockStates(newBlockStates, properties);
	}
}
