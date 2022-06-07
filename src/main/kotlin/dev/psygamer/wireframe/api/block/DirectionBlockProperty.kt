package dev.psygamer.wireframe.api.block

import net.minecraft.util.Direction

class DirectionBlockProperty(
	propertyName: String,
) : EnumBlockProperty<Direction>(propertyName, *net.minecraft.util.Direction.values())