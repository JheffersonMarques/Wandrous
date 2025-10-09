package com.hakimen.wandrous;

import com.hakimen.wandrous.common.registers.Registration;
import com.hakimen.wandrous.config.ClientConfig;
import com.hakimen.wandrous.config.ServerConfig;
import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Wandrous.MODID)
public class Wandrous
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "wandrous";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public Wandrous(ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        container.registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC);



        Registration.register(container.getEventBus());
    }
}
