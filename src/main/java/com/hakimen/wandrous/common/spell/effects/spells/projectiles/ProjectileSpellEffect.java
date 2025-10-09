package com.hakimen.wandrous.common.spell.effects.spells.projectiles;

import com.hakimen.wandrous.common.api.SpellContext;
import com.hakimen.wandrous.common.api.SpellEffect;
import com.hakimen.wandrous.common.api.SpellStatus;
import com.hakimen.wandrous.common.entity.static_spell.PlasmaBeamEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;


public class ProjectileSpellEffect extends SpellEffect {
    public ProjectileSpellEffect() {
    }

    public static void shootProjectile(Projectile self, SpellContext context){
        Entity caster = context.getCaster();
        SpellStatus status = context.getStatus();

        if(caster.equals(context.getOriginalCaster())){
            self.setPos(self.getX(), self.getY() - self.getBbHeight()/2f, self.getZ());
        }

        if (caster instanceof LivingEntity livingEntity) {
            float yRot = livingEntity.getYRot() + Math.round(context.getSplit() / 2.0) * (10 + status.getSpread() * 10) * (context.getSplit() % 2 == 1 ? -1 : 1);
            float xRot = livingEntity.getXRot();
            self.shootFromRotation(livingEntity,xRot, yRot, 0,  status.getSpeed() < 0 ? 0.1f : status.getSpeed(), status.getSpread() * 10);
            self.setXRot(xRot);
            self.setYRot(yRot);
        } else {
            float yRot = Math.round(context.getSplit() / 2.0) * (10 + status.getSpread() * 10) * (context.getSplit() % 2 == 1 ? -1 : 1);
            if(self instanceof PlasmaBeamEntity){
                yRot = caster.getYRot();
            }
            self.setYRot(yRot);
            self.setDeltaMovement(caster.getDeltaMovement().yRot((float) Math.toRadians(yRot)));
        }
    }
}
