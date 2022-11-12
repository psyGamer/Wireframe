package dev.psygamer.wireframe.nativeapi.data

import net.minecraft.nbt.CompoundNBT
import net.minecraft.world.storage.WorldSavedData
import dev.psygamer.wireframe.api.data.WorldSaveData
import dev.psygamer.wireframe.nativeapi.wfWrapped

internal class NativeWorldSaveData(val worldSaveData: WorldSaveData) : WorldSavedData(worldSaveData.name) {

	override fun save(compound: CompoundNBT): CompoundNBT {
		worldSaveData.saveNBT(compound.wfWrapped)
		return compound
	}

	override fun load(compound: CompoundNBT) {
		worldSaveData.loadNBT(compound.wfWrapped)
	}
}