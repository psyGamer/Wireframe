package dev.psygamer.wireframe.api.data

import dev.psygamer.wireframe.util.TagCompound

abstract class WorldSaveData(val name: String) {

	abstract fun saveNBT(compound: TagCompound)
	abstract fun loadNBT(compound: TagCompound)
}