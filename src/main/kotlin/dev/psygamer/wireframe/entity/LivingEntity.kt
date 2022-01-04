package dev.psygamer.wireframe.entity

interface LivingEntity : Entity {
	
	val maxHealth: Int
	var health: Int
	
	var leashHolder: Player?
	
	fun canBeLeashedTo(player: Player): Boolean
	fun isLeashedTo(player: Player): Boolean
	
	fun unleash() {
		unleash(true)
	}
	
	fun unleash(dropLeashItem: Boolean)
}