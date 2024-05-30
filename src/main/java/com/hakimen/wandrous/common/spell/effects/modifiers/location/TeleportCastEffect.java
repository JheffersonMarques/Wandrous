package com.hakimen.wandrous.common.spell.effects.modifiers.location;

import com.hakimen.wandrous.common.entity.projectiles.SpellCastingProjectile;
import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TeleportCastEffect extends SpellEffect {
    public TeleportCastEffect() {
        setKind(MODIFIER);
        setStatus(
                new SpellStatus()
                .setManaDrain(40)
        );
    }

    @Override
    public void cast(SpellContext context) {
        SpellContext clone = context.clone();

        Level level = context.getLevel();
        Vec3 location = context.getLocation();
        List<Entity> allEntities = level.getEntities(clone.getOriginalCaster(), AABB.ofSize(location,16,16,16), entity -> entity instanceof LivingEntity && !context.getHit().contains(entity));

        Entity closestEntity = null;
        for (Entity entity : allEntities) {
            if(closestEntity == null){
                closestEntity = entity;
            }else if(closestEntity.distanceToSqr(location) > entity.distanceToSqr(location)){
                closestEntity = entity;
            }
        }

        if(closestEntity != null){
            clone.setCastPositionModified(true);
            if(closestEntity instanceof LivingEntity entity){
                clone.getHit().add(entity);
            }
            clone.setLocation(closestEntity.getPosition(0));
        }

        for (Node<SpellEffect> child : clone.getNode().getChildren()) {
            child.getData().cast(clone.setNode(child));
        }
    }
}
