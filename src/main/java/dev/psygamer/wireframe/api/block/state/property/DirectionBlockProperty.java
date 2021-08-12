package dev.psygamer.wireframe.api.block.state.property;

import net.minecraft.util.Direction;

public class DirectionBlockProperty extends EnumBlockProperty<Direction> {
	
	public DirectionBlockProperty(final String propertyName) {
		super(propertyName, Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);
	}
}