package dev.psygamer.wireframe.block.state.property;

import net.minecraft.util.Direction;

/** An {@link BlockProperty} implementation for {@link Direction Directions}. */
public class DirectionBlockProperty extends EnumBlockProperty<Direction> {
	
	/**
	 * @param propertyName The {@link BlockProperty}'s name.
	 */
	public DirectionBlockProperty(final String propertyName) {
		super(propertyName, Direction.values());
	}
}
