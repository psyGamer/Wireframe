package dev.psygamer.wireframe.block.state.property;

import net.minecraft.util.Direction;

public class DirectionBlockProperty extends EnumBlockProperty<Direction> {
	
	public DirectionBlockProperty(final String propertyName) {
		super(propertyName, Direction.values());
	}
}
