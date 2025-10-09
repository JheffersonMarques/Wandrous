package com.hakimen.wandrous.client.entity;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.client.mover.IMoverRendererRegister;
import com.hakimen.wandrous.client.utils.VertexProcessorBuilder;
import com.hakimen.wandrous.common.api.mover.ISpellMover;
import com.hakimen.wandrous.common.entity.projectiles.BlackHoleProjectile;
import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class BlackHoleProjectileRenderer extends EntityRenderer<BlackHoleProjectile> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/entity/black_hole_projectile.png");


    public BlackHoleProjectileRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(BlackHoleProjectile timerEntity) {
        return TEXTURE;
    }

    @Override
    public void render(BlackHoleProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        List<ISpellMover> movers = SpellCastingProjectile.NBTToMoverList(pEntity.getEntityData().get(SpellCastingProjectile.MOVER_DATA));
        IMoverRendererRegister.render(movers, pEntity,pEntityYaw,pPartialTick,pPoseStack,pBuffer,pPackedLight);
        float scale = 1.25f;
        pPoseStack.pushPose();



        pPoseStack.scale(scale,scale,scale);
        pPoseStack.translate(0.0D, 0.25f, 0.0D);
        VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.leash());

        VertexProcessorBuilder.drawSphere(vertexconsumer, pPoseStack, 1,32, 32, 0,0,0.4f,1,15);

        VertexConsumer borderConsumer = pBuffer.getBuffer(RenderType.debugFilledBox());
        VertexProcessorBuilder.drawSphere(borderConsumer, pPoseStack, 1.1f,32, 32, 0.255f, 0.439f, 0.5f,0.05f,15);
        VertexProcessorBuilder.drawSphere(borderConsumer, pPoseStack, 1.2f,32, 32, 0.255f, 0.118f, 0.569f,0.1f,15);

        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected int getBlockLightLevel(BlackHoleProjectile pEntity, BlockPos pPos) {
        return 15;
    }

}
