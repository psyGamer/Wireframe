package dev.psygamer.wireframe.api.client.render.blockentity

import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.client.render.PoseStack

interface BlockEntityRenderer<T : BlockEntity> {

	fun render(blockEntity: T, poseStack: PoseStack)
}