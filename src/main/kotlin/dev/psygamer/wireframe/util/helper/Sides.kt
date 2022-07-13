package dev.psygamer.wireframe.util.helper

import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.thread.SidedThreadGroups
import dev.psygamer.wireframe.api.world.World

inline fun onlyOnLogicalClient(block: () -> Unit) {
	if (Thread.currentThread().threadGroup == SidedThreadGroups.CLIENT) block()
}

inline fun onlyOnLogicalServer(block: () -> Unit) {
	if (Thread.currentThread().threadGroup == SidedThreadGroups.SERVER) block()
}

inline fun onlyOnLogicalClient(world: World, block: () -> Unit) {
	if (world.isClientSide) block()
}

inline fun onlyOnLogicalServer(world: World, block: () -> Unit) {
	if (world.isClientSide) block()
}

inline fun onlyOnPhysicalClient(crossinline block: () -> Unit) {
	DistExecutor.safeCallWhenOn(Dist.CLIENT) { DistExecutor.SafeCallable { block() } }
}

inline fun onlyOnPhysicalServer(crossinline block: () -> Unit) {
	DistExecutor.safeCallWhenOn(Dist.CLIENT) { DistExecutor.SafeCallable { block() } }
}