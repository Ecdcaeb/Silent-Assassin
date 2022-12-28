package com.Hileb.moremomostories.otherMods.SlashBlade;

import com.Hileb.moremomostories.IdlFramework;
import com.Hileb.moremomostories.otherMods.SlashBlade.SA.ModSA;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.BladeTest;
import com.Hileb.moremomostories.otherMods.SlashBlade.SlashBlade.IBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;

import java.util.ArrayList;
import java.util.List;

public class SlashBladeUtil {
    private final List<Object> bladeList= new ArrayList<>();
    public SlashBladeUtil(){
        bladeList.add(new BladeTest());//测试刀
    }
    public void registerBlade(){
        for(int i=0;i<bladeList.size();i++){//批量注册
            if(bladeList.get(i) instanceof IBlade){
                IdlFramework.Log("register Blade:%s",((IBlade)(bladeList.get(i))).getName());
                ((IBlade)(bladeList.get(i))).registerBlade();
                ((IBlade)(bladeList.get(i))).registerRecipe();
            }
        }
    }
    public void registerSA(){
        ItemSlashBlade.specialAttacks.put(280, ModSA.sa1);
    }
}
