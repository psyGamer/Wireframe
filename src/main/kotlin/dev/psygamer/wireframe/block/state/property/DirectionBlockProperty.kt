package dev.psygamer.wireframe.block.state.property

class DirectionBlockProperty(
	propertyName: String
) : EnumBlockProperty<net.minecraft.util.Direction>(propertyName, *net.minecraft.util.Direction.values())