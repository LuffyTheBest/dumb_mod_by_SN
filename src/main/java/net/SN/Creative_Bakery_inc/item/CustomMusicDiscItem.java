package net.SN.Creative_Bakery_inc.item;

import net.minecraft.world.item.RecordItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.RegistryObject;

public class CustomMusicDiscItem extends RecordItem {
    public CustomMusicDiscItem(int analogOutput, RegistryObject<SoundEvent> soundEvent, Properties properties, int lengthInTicks) {
        super(analogOutput, () -> soundEvent.get(), properties, lengthInTicks);
    }
}
