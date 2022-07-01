package dev.psygamer.wireframe.util.helper

import dev.psygamer.wireframe.event.api.EventSubscriber
import dev.psygamer.wireframe.event.nativeapi.NativeForgeEventBusSubscriber

sealed class CommonVariableObserver<T>(val variableGetter: () -> T, val onValueChanged: (T, T) -> Unit) {

	@Suppress("UNUSED_PARAMETER")
	@NativeForgeEventBusSubscriber
	companion object {

		@JvmStatic
		protected val tickingCommonObservers = emptySet<CommonVariableObserver<*>>().toMutableSet()

		@JvmStatic
		protected val tickingClientObservers = emptySet<ClientVariableObserver<*>>().toMutableSet()

		@JvmStatic
		protected val tickingServerObservers = emptySet<ServerVariableObserver<*>>().toMutableSet()

		@JvmStatic
		@EventSubscriber
		private fun onClientTick(event: net.minecraftforge.event.TickEvent.ClientTickEvent) {
			tickingClientObservers.forEach(ClientVariableObserver<*>::checkIfValueChanged)
			tickingCommonObservers.forEach(CommonVariableObserver<*>::checkIfValueChanged)
		}

		@JvmStatic
		@EventSubscriber
		private fun onServerTick(event: net.minecraftforge.event.TickEvent.ServerTickEvent) {
			tickingServerObservers.forEach(ServerVariableObserver<*>::checkIfValueChanged)
			tickingCommonObservers.forEach(CommonVariableObserver<*>::checkIfValueChanged)
		}
	}

	open var checkInterval: Int? = null
		set(value) {
			field = value
			checkCountdown = value

			if (value == null)
				tickingCommonObservers.remove(this)
			else
				tickingCommonObservers.add(this)
		}
	private var checkCountdown: Int? = null

	private var prevValue: T = variableGetter()

	fun checkIfValueChanged() {
		val currentValue = variableGetter()
		if (currentValue != prevValue)
			onValueChanged(currentValue, prevValue)
	}
}

class ClientVariableObserver<T>(variableGetter: () -> T, onValueChanged: (T, T) -> Unit) :
	CommonVariableObserver<T>(variableGetter, onValueChanged) {

	override var checkInterval: Int?
		get() = super.checkInterval
		set(value) {
			super.checkInterval = value

			if (value == null)
				tickingClientObservers.remove(this)
			else
				tickingClientObservers.add(this)
		}
}

class ServerVariableObserver<T>(variableGetter: () -> T, onValueChanged: (T, T) -> Unit) :
	CommonVariableObserver<T>(variableGetter, onValueChanged) {

	override var checkInterval: Int?
		get() = super.checkInterval
		set(value) {
			super.checkInterval = value

			if (value == null)
				tickingServerObservers.remove(this)
			else
				tickingServerObservers.add(this)
		}
}