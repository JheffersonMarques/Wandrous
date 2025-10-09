package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.common.entity.static_spell.PlasmaBeamEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class PlasmaBeamEntityRenderer extends EntityRenderer<PlasmaBeamEntity> {
    public PlasmaBeamEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(PlasmaBeamEntity pEntity) {
        return ResourceLocation.withDefaultNamespace("empty");
    }

    @Override
    public void render(PlasmaBeamEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        pPoseStack.pushPose();




        ///DebugRenderer.renderFilledUnitCube(pPoseStack,pBuffer, pEntity.getOnPos().above(),1,1,1,1);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    public static void addChainedFilledBoxVertices(
            PoseStack pPoseStack,
            VertexConsumer pConsumer,
            float pMinX,
            float pMinY,
            float pMinZ,
            float pMaxX,
            float pMaxY,
            float pMaxZ,
            float pRed,
            float pGreen,
            float pBlue,
            float pAlpha
    ) {
        Matrix4f matrix4f = pPoseStack.last().pose();
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMinY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMinX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMinZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
        pConsumer.addVertex(matrix4f, pMaxX, pMaxY, pMaxZ).setColor(pRed, pGreen, pBlue, pAlpha);
    }
}
