package dev.psygamer.wireframe.api.block.state.property

import net.minecraft.util.Direction

class DirectionBlockProperty(
	propertyName: String
) : EnumBlockProperty<Direction>(propertyName, *net.minecraft.util.Direction.values())