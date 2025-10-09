package com.hakimen.wandrous.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ServerConfig {

    public enum IFrameConfig {
        ALL,
        PLAYERS_ONLY,
        MOBS_ONLY,
        NONE,
    }

    static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue RECHARGE_CRYSTAL = BUILDER
            .comment("The reload time for the recharge crystal")
            .defineInRange("recharge_crystal", 60, 0, 200);

    public static final ModConfigSpec.IntValue GREATER_RECHARGE_CRYSTAL = BUILDER
            .comment("The reload time for the greater recharge crystal")
            .defineInRange("greater_recharge_crystal", 40, 0, 200);

    public static final ModConfigSpec.DoubleValue STRENGTH_DAMAGE_MODIFIER = BUILDER
            .comment("The damage modifier of having the Strength effect on the caster")
            .defineInRange("strength_damage_modifier", 0.5, 0, 2);

    public static final ModConfigSpec.EnumValue<IFrameConfig> IFRAME_CONFIG = BUILDER.comment("How to apply the reduce iframe spell")
            .defineEnum("reduce_iframe_rule", IFrameConfig.ALL);

    public static final ModConfigSpec.BooleanValue SHOULD_GIVE_GUIDE_BOOK = BUILDER.comment("Should the guide book be given on first spawn")
            .define("should_give_guide_book", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
