package com.hakimen.wandrous.common.entity.projectiles;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStack;
import com.hakimen.wandrous.common.spell.effects.modifiers.MoverSpellEffect;
import com.hakimen.wandrous.common.spell.effects.modifiers.ProjectileHitEffect;
import com.hakimen.wandrous.common.spell.mover.ISpellMover;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public class SpellCastingProjectile extends ThrowableProjectile {

    int maxTicks;

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, double pX, double p_37458_, double pY, Level p_37460_) {
        super(pEntityType, pX, p_37458_, pY, p_37460_);
    }

    protected SpellCastingProjectile(EntityType<? extends ThrowableProjectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        super(pEntityType, pShooter, pLevel);
    }


    protected static void onHitBlock(Projectile self, BlockHitResult pResult, SpellContext context){

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(context, self.level(), pResult.getBlockPos(), self.level().getBlockState(pResult.getBlockPos()));
        });

        if (context.getNode().getData().getEffect().hasKind(SpellEffect.TRIGGER)) {
            self.setDeltaMovement(new Vec3(self.getDeltaMovement().toVector3f().reflect(Vec3.atLowerCornerOf(pResult.getDirection().getNormal()).toVector3f())));
            context.getNode().getChildren().forEach(
                    (child) -> child.getData().getEffect().cast(context.setNode(child).setLocation(pResult.getLocation()))
            );
        }
    }


    protected static void onHitEntity(Projectile self, EntityHitResult pResult, SpellContext context) {
        if (pResult.getEntity() instanceof LivingEntity entity) {
            context.getHit().add(entity);
        }

        List<ProjectileHitEffect> effects = addProjectileEffects(context.getNode());

        effects.forEach(projectileHitEffect -> {
            projectileHitEffect.onHitBlock(context, self.level(), pResult.getEntity().getOnPos(), self.level().getBlockState(pResult.getEntity().getOnPos()));
            projectileHitEffect.onHitEntity(context, pResult.getEntity());
        });

        if (context.getNode().getData().getEffect().hasKind(SpellEffect.TRIGGER)) {
            self.setDeltaMovement(self.getDeltaMovement().multiply(1, -1, 1));
            context.getNode().getChildren().forEach(
                    (child) -> child.getData().getEffect().cast(context.setNode(child).setLocation(pResult.getLocation()))
            );
        }

        pResult.getEntity().hurt(self.damageSources().source(DamageTypes.MAGIC, context.getOriginalCaster()), context.getStatus().getDamage());
    }


    protected static void onTimeEnd(Projectile self, SpellContext context) {
        if (context.getNode().getData().getEffect().hasKind(SpellEffect.TIMER)) {
            context.getNode().getChildren().forEach(
                    (child) -> child.getData().getEffect().cast(context.setNode(child).setLocation(self.getPosition(0)))
            );
        }
    }

    private static List<ProjectileHitEffect> addProjectileEffects(Node<SpellStack> node){
        List<ProjectileHitEffect> effects = new ArrayList<>();

        if(node.getParent() != null &&  node.getParent().getData().getEffect().hasKind(SpellEffect.MODIFIER)){
            SpellEffect effect = node.getParent().getData().getEffect();
            if(effect instanceof ProjectileHitEffect eff){
                effects.add(eff);
            }
            effects.addAll(addProjectileEffects(node.getParent()));
        }
        return effects;
    }

    static List<ISpellMover> getMovers(Node<SpellStack> node){
        List<ISpellMover> movers = new ArrayList<>();

        if(node.getParent() != null &&  node.getParent().getData().getEffect().hasKind(SpellEffect.MODIFIER)){
            SpellEffect effect = node.getParent().getData().getEffect();
            if(effect instanceof MoverSpellEffect eff){
                movers.add(eff.getMover());
            }
            movers.addAll(getMovers(node.getParent()));
        }
        return movers;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("MaxTicks", maxTicks);
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        maxTicks = pCompound.getInt("MaxTicks");
        super.readAdditionalSaveData(pCompound);
    }

    public float getInertia(){
        return 0;
    }

    public float getFluidInertia(){
        return 0;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {

    }

    @Override
    public void tick() {
        super.tick();

        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult)) {
            this.hitTargetOrDeflectSelf(hitresult);
        }

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            for (int i = 0; i < 4; i++) {
                float f1 = 0.25F;
                this.level().addParticle(ParticleTypes.BUBBLE, d0 - vec3.x * 0.25, d1 - vec3.y * 0.25, d2 - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
            }

            f = getFluidInertia();
        } else {
            f = getInertia();
        }

        this.setDeltaMovement(vec3.scale((double)f));
        this.applyGravity();
        this.setPos(d0, d1, d2);
    }

    public int getMaxTicks() {
        return maxTicks;
    }
}
