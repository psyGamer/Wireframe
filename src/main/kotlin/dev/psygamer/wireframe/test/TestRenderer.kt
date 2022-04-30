package dev.psygamer.wireframe.test

import net.minecraft.client.renderer.IRenderTypeBuffer
import net.minecraft.item.Items
import dev.psygamer.wireframe.api.client.RenderHelper3D
import dev.psygamer.wireframe.api.client.render.*
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.Color
import dev.psygamer.wireframe.util.helper.using
import dev.psygamer.wireframe.util.math.vector.Vector3d

object TestRenderer {
	
	fun render(te: NativeBlockEntity, buf: IRenderTypeBuffer, ms: PoseStack) {
		var context = RenderingContext(
			Vector3d(te.blockPos.x.toDouble(), te.blockPos.y.toDouble(), te.blockPos.z.toDouble()), Vector3d.ZERO, Vector3d.ONE,
			skyLight = te.level!!.wfWrapped.getSkyLightLevel(te.blockPos.wfWrapped),
			blockLight = te.level!!.wfWrapped.getBlockLightLevel(te.blockPos.wfWrapped),
			
			poseStack = ms, renderBuffer = buf,
		)
		
		if (te.blockPos.x == 69 && te.blockPos.z == 420)
			context = RenderingContext(
				Vector3d(69.0, 70.0, 420.0), Vector3d(30.0, 60.0, 90.0), Vector3d(1.2, 2.4, 3.6),
				skyLight = 15, blockLight = 15, renderBuffer = buf, poseStack = ms
			)
		
		if (te.blockPos.x == 68)
			using(ms.push()) {
				RenderHelper3D.renderText(context, "Hallo Welt![0]", Color.LIGHT_RED)
//				ms.translate(0.0, 3.0, 0.0)
				RenderHelper3D.renderCenteredText(context, "Hallo Welt![1]", Color.LIGHT_GREEN)
//				ms.translate(0.0, 3.0, 0.0)
				RenderHelper3D.renderTextNoShadow(context, "Hallo Welt![2]", Color.LIGHT_BLUE)
//				ms.translate(0.0, 3.0, 0.0)
				RenderHelper3D.renderCenteredTextNoShadow(context, "ArrayIndexOutOfBoundsException", Color.WHITE,
														  Color(0xAA00AA, 0x88))
			}
		
		if (te.blockPos.z == 419)
			using(ms.push()) {
				ms.translate(te.blockPos.y - 69.0, te.blockPos.y - 69.0, te.blockPos.y - 69.0)
				RenderHelper3D.renderItemStack(context, Items.OAK_BOAT.defaultInstance.wfWrapped)
			}
	}
}