package dev.psygamer.wireframe.gui

class BoxModelStack(
	val contentWidth: Int,
	val contentHeight: Int,
) {

	val stack = ArrayDeque<Entry>().apply {
		add(
			Entry(
				0, 0, 0, 0,
				0, 0, 0, 0,
				0, 0, 0, 0,
				0, 0, 0, 0,
			)
		)
	}

	data class Entry(
		var paddingTop: Int,
		var paddingRight: Int,
		var paddingBottom: Int,
		var paddingLeft: Int,

		var outlineTop: Int,
		var outlineRight: Int,
		var outlineBottom: Int,
		var outlineLeft: Int,

		var borderTop: Int,
		var borderRight: Int,
		var borderBottom: Int,
		var borderLeft: Int,

		var marginTop: Int,
		var marginRight: Int,
		var marginBottom: Int,
		var marginLeft: Int,
	)

	fun push() {
		stack.addLast(stack.last())
	}

	fun pop() {
		stack.removeLast()
	}

	val last = stack.last()
}

var BoxModelStack.Entry.padding
	set(padding) {
		paddingTop = padding
		paddingRight = padding
		paddingBottom = padding
		paddingLeft = padding
	}
	get() = maxOf(paddingTop, paddingRight, paddingBottom, paddingLeft)

var BoxModelStack.Entry.outline
	set(margin) {
		outlineTop = margin
		outlineRight = margin
		outlineBottom = margin
		outlineLeft = margin
	}
	get() = maxOf(outlineTop, outlineRight, outlineBottom, outlineLeft)

var BoxModelStack.Entry.border
	set(margin) {
		borderTop = margin
		borderRight = margin
		borderBottom = margin
		borderLeft = margin
	}
	get() = maxOf(borderTop, borderRight, borderBottom, borderLeft)