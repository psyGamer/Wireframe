package dev.psygamer.wireframe.gui

import dev.psygamer.wireframe.util.math.Dimension2I

class BoxModelStack(contentWidth: Int, contentHeight: Int) {

	val stack = ArrayDeque<Entry>().apply {
		add(
			Entry(
				contentWidth, contentHeight,
				0, 0, 0, 0,
				0, 0, 0, 0,
			)
		)
	}

	data class Entry(
		var contentWidth: Int,
		var contentHeight: Int,

		var marginTop: Int,
		var marginRight: Int,
		var marginBottom: Int,
		var marginLeft: Int,

		var borderTop: Int,
		var borderRight: Int,
		var borderBottom: Int,
		var borderLeft: Int,
	)

	fun push() {
		stack.addLast(stack.last())
	}

	fun pop() {
		stack.removeLast()
	}

	val last = stack.last()
}

var BoxModelStack.Entry.contentSize
	set(size) {
		contentWidth = size.width
		contentHeight = size.height
	}
	get() = Dimension2I(contentWidth, contentHeight)

var BoxModelStack.Entry.margin
	set(margin) {
		marginTop = margin
		marginRight = margin
		marginBottom = margin
		marginLeft = margin
	}
	get() = maxOf(marginTop, marginRight, marginBottom, marginLeft)

var BoxModelStack.Entry.border
	set(margin) {
		borderTop = margin
		borderRight = margin
		borderBottom = margin
		borderLeft = margin
	}
	get() = maxOf(borderTop, borderRight, borderBottom, borderLeft)