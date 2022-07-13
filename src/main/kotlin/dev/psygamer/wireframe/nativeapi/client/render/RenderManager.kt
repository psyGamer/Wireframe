package dev.psygamer.wireframe.nativeapi.client.render

import dev.psygamer.wireframe.nativeapi.client.render.context.RenderingContext

object RenderManager {
	
	val currentContext: RenderingContext
		get() {
			if (_currentContext == null)
				throw IllegalStateException("Cannot access rendering context whilst not rendering!")
			return _currentContext!!
		}
	
	val rendering
		get() = _currentContext != null
	
	private var _currentContext: RenderingContext? = null
	
	fun startContext(context: RenderingContext) {
		if (_currentContext != null)
			throw IllegalStateException("Cannot start another batch before ending the previous one!")
		_currentContext = context
	}
	
	fun endContext() {
		_currentContext = null
	}
}