package net.SN.Creative_Bakery_inc.item;

import net.SN.Creative_Bakery_inc.creativebakeryinc;
import net.SN.Creative_Bakery_inc.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, creativebakeryinc.MOD_ID);

    public static final RegistryObject<Item> HAMMER_ITEM = ITEMS.register("hammer",
            () -> new HammerItem(Tiers.IRON, 5f, -2.5f,
                    new Item.Properties().durability(400)));

    public static final RegistryObject<Item> LEGENDARY_HAMMER_ITEM = ITEMS.register("legendary_hammer",
            () -> new HammerItem(Tiers.NETHERITE, 8f, -2.8f,
                    new Item.Properties().durability(2500)));

    // Try direct RecordItem with lambda supplier - exactly like vanilla
    public static final RegistryObject<Item> HAMMER_OF_JUSTICE = ITEMS.register("hammer_of_justice",
            () -> new RecordItem(6, ModSounds.HAMMER_OF_JUSTICE, new Item.Properties().stacksTo(1), 3140));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
