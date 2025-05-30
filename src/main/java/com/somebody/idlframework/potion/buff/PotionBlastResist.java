package com.somebody.idlframework.potion.buff;

import java.util.Collection;

import com.somebody.idlframework.util.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PotionBlastResist extends BasePotion {
    public PotionBlastResist(boolean isBadEffectIn, int liquidColorIn, String name, int icon) {
        super(isBadEffectIn, liquidColorIn, name, icon);
        knockbackResistanceRatio = 0.1f;
        knockbackResistanceRatioPerLevel = 0.1f;

        resistancePerLevel = 0.25f;
    }

    @SubscribeEvent
    public static void onCreatureHurt(LivingHurtEvent evt) {
        World world = evt.getEntity().getEntityWorld();
        EntityLivingBase hurtOne = evt.getEntityLiving();

        if (evt.isCanceled() || !evt.getSource().isExplosion())
        {
            return;
        }

        //Explosion Damage Reduction
        Collection<PotionEffect> activePotionEffects = hurtOne.getActivePotionEffects();
        for (int i = 0; i < activePotionEffects.size(); i++) {
            PotionEffect buff = (PotionEffect)activePotionEffects.toArray()[i];
            if (buff.getPotion() instanceof PotionBlastResist)
            {
                PotionBlastResist modBuff = (PotionBlastResist)buff.getPotion();
                if (!world.isRemote)
                {
                    float reduceRatio = modBuff.resistancePerLevel * (buff.getAmplifier());
                    evt.setAmount(Math.max(1 - reduceRatio, 0f) * evt.getAmount());
                }
            }
        }
    }
}
