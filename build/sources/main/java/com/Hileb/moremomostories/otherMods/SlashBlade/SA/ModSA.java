package com.Hileb.moremomostories.otherMods.SlashBlade.SA;

import com.Hileb.moremomostories.otherMods.SlashBlade.SA.Entity.EntityRain;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModSA {
    public static final SpecialAttackBase sa1=new SpecialAttackBase() {
        @Override
        public String toString() {
            return "sa1";
        }

        @Override
        public void doSpacialAttack(ItemStack itemStack, EntityPlayer entityPlayer) {
            //entityPlayer.sendMessage(new TextComponentString("this is sa speaking"));
            Random random=new Random(entityPlayer.world.getSeed()+entityPlayer.getUniqueID().hashCode()+(int)entityPlayer.posX);
            if (!entityPlayer.world.isRemote){
                List<EntityRain> rains=new ArrayList<>();
                for(int y=0;y<=5;y++){
                    for(int i=0;i<=9;i++){
                        for(int j=0;j<=9;j++){
                            EntityRain entity=new EntityRain(entityPlayer.world,itemStack,entityPlayer);
                            entity.setPosition(entityPlayer.posX-4+i+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posY+y+((double) (random.nextInt(10)/10)-0.5f),entityPlayer.posZ-4+j+((double) (random.nextInt(10)/10)-0.5f));
                            entityPlayer.world.spawnEntity(entity);
                            rains.add(entity);
                        }
                    }
                }
            }
        }
    };
}
