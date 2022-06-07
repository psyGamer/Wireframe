package dev.psygamer.wireframe.api.item.util

data class ClickResultContainer<T>(val obj: T, val result: ClickResult) {

	companion object {

		@JvmStatic
		fun <T> accept(obj: T): ClickResultContainer<T> {
			return ClickResultContainer(obj, ClickResult.ACCEPTED)
		}

		@JvmStatic
		fun <T> reject(obj: T): ClickResultContainer<T> {
			return ClickResultContainer(obj, ClickResult.REJECTED)
		}

		@JvmStatic
		fun <T> pass(obj: T): ClickResultContainer<T> {
			return ClickResultContainer(obj, ClickResult.PASS)
		}
	}
}