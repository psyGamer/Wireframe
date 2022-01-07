package dev.psygamer.wireframe.util

data class Color(
	val red: Float, val green: Float, val blue: Float, val alpha: Float = 1.0f
) {
	
	constructor(red: Long, green: Long, blue: Long, alpha: Long = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)
	
	constructor(red: Int, green: Int, blue: Int, alpha: Int = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)
	
	constructor(red: Short, green: Short, blue: Short, alpha: Short = 1)
			: this(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f)
	
	constructor(red: UByte, green: UByte, blue: UByte, alpha: UByte = 1u)
			: this(red.toInt() / 255.0f, green.toInt() / 255.0f, blue.toInt() / 255.0f, alpha.toInt() / 255.0f)
	
	constructor(color: Long) : this(
		(color shr 0 and 255) / 255.0f,
		(color shr 8 and 255) / 255.0f,
		(color shr 16 and 255) / 255.0f,
		(color shr 24 and 255) / 255.0f
	)
	
	companion object {
		
		/** @throws NumberFormatException If the [color] is not a valid hexadecimal number. */
		fun parseString(color: String): Color {
			return when {
				color.startsWith("#") -> Color(color.substring(1).toLong())
				color.startsWith("0x", true) -> Color(color.substring(2).toLong())
				
				else -> Color(color.toLong())
			}
		}
	}
}