package dev.psygamer.wireframe.api.item

import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.TagCompound

class ItemStack {

	val item: Item

	val count: Int
		get() = mcNative.count

	val tag: TagCompound?
		get() = mcNative.tag?.wfWrapped

	internal val mcNative: net.minecraft.item.ItemStack

	@JvmOverloads
	constructor(item: Item, count: Int = 1, tagData: TagCompound = TagCompound()) {
		this.item = item
		mcNative = net.minecraft.item.ItemStack(item.mcNative, count, tagData.mcNative)
	}

	internal constructor(internal: net.minecraft.item.ItemStack) {
		item = Item(internal.item)
		this.mcNative = internal
	}

	fun grow(amount: Int) {
		mcNative.grow(amount)
	}

	fun shrink(amount: Int) {
		mcNative.shrink(amount)
	}
}