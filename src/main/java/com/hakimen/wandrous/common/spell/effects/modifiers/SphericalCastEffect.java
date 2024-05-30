package com.hakimen.wandrous.common.spell.effects.modifiers;

import com.hakimen.wandrous.common.spell.SpellContext;
import com.hakimen.wandrous.common.spell.SpellEffect;
import com.hakimen.wandrous.common.spell.SpellStatus;
import com.hakimen.wandrous.common.utils.data.Node;

public class SphericalCastEffect extends MultiCastEffect {

    public SphericalCastEffect(int count) {
        super(count);
        this.setKind(MODIFIER);
        this.setStatus(new SpellStatus()
                .setManaDrain(count * 5));
    }

    @Override
    public void cast(SpellContext context) {
        float i = 1;
        for (Node<SpellEffect> child : context.getNode().getChildren()) {
            SpellContext context1 = context.clone();
            context1.setLocation(context.getLocation().add((float) Math.cos(i) * castCount, (float) 0 * castCount, (float) Math.sin(i) * castCount));
            context1.setCastPositionModified(true);
            child.getData().cast(context1.setNode(child));
            i++;
        }
    }
}
