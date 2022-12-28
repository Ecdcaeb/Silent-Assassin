package com.Hileb.silent_assassin.util.sound;

import com.Hileb.silent_assassin.util.ModSoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvent extends SoundEvent
{

    public ModSoundEvent(String path) {
        super(new ResourceLocation("silent_assassin", path));
        ModSoundHandler.SOUNDS.add(this);
        this.setRegistryName(path);
    }
}
