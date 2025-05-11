package com.comical.examplemod.entity;

import com.comical.examplemod.Main;
import com.comical.examplemod.entity.creatures.moroon.EntityMoroonUnitBase;
import com.comical.examplemod.entity.creatures.render.RenderBullet;
import com.comical.examplemod.entity.creatures.render.RenderMoroonHumanoid;
import com.comical.examplemod.entity.projectiles.EntityIdlProjectile;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMoroonUnitBase.class, RenderMoroonHumanoid::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityIdlProjectile.class, renderManager -> new RenderBullet<>(renderManager, new ResourceLocation(Main.MODID,
                "textures/entity/projectiles/bullet_norm.png")));
    }
}
