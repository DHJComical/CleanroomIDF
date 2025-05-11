package com.somebody.idlframework.entity;

import com.somebody.idlframework.Main;
import com.somebody.idlframework.entity.creatures.moroon.EntityMoroonUnitBase;
import com.somebody.idlframework.entity.creatures.render.RenderBullet;
import com.somebody.idlframework.entity.creatures.render.RenderMoroonHumanoid;
import com.somebody.idlframework.entity.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, RenderMoroonHumanoid::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));
    }
}
