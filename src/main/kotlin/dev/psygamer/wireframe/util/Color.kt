package dev.psygamer.wireframe.util

data class Color(
	val red: Float, val green: Float, val blue: Float, val alpha: Float = 1.0f,
) {

	companion object {

		val TRANSPARENT = Color(0x000000, 0x00)

		val BLACK = Color(0x000000, 0xFF)

		val DARK_RED = Color(0xAA0000, 0xFF)
		val DARK_GREEN = Color(0x00AA00, 0xFF)
		val DARK_BLUE = Color(0x0000AA, 0xFF)

		val DARK_AQUA = Color(0x00AAAA, 0xFF)
		val DARK_PURPLE = Color(0xAA00AA, 0xFF)
		val DARK_YELLOW = Color(0xFFAA00, 0xFF)

		val DARK_GRAY = Color(0x555555, 0xFF)
		val LIGHT_GRAY = Color(0xAAAAAA, 0xFF)

		val LIGHT_RED = Color(0xFF5555, 0xFF)
		val LIGHT_GREEN = Color(0x55FF55, 0xFF)
		val LIGHT_BLUE = Color(0x5555FF, 0xFF)

		val LIGHT_AQUA = Color(0x55FFFF, 0xFF)
		val LIGHT_PURPLE = Color(0xFF55FF, 0xFF)
		val LIGHT_YELLOW = Color(0xFFFF55, 0xFF)

		val WHITE = Color(0xFFFFFF, 0xFF)

		/** @throws NumberFormatException If the [color] is not a valid hexadecimal number. */
		fun parseString(color: String): Color {
			return when {
				color.startsWith("#") -> Color(color.substring(1).toLong())
				color.startsWith("0x", true) -> Color(color.substring(2).toLong())

				else -> Color(color.toLong())
			}
		}
	}

	constructor(red: Long, green: Long, blue: Long, alpha: Long = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)

	constructor(red: Int, green: Int, blue: Int, alpha: Int = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)

	constructor(red: Short, green: Short, blue: Short, alpha: Short = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)

	constructor(red: UByte, green: UByte, blue: UByte, alpha: UByte = 1u)
			: this(red.toInt() / 255.0f, green.toInt() / 255.0f, blue.toInt() / 255.0f, alpha.toInt() / 255.0f)

	constructor(color: Long) : this(
		(color shr 24 and 255) / 255.0f,
		(color shr 16 and 255) / 255.0f,
		(color shr 8 and 255) / 255.0f,
		(color shr 0 and 255) / 255.0f
	)

	constructor(rgb: Int, alpha: Int) : this(
		(rgb shr 16 and 255) / 255.0f,
		(rgb shr 8 and 255) / 255.0f,
		(rgb shr 0 and 255) / 255.0f,
		alpha / 255.0f
	)
}