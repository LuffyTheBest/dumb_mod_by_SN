package net.SN.Creative_Bakery_inc.item;

import net.SN.Creative_Bakery_inc.creativebakeryinc;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, creativebakeryinc.MOD_ID);

    // Regular Hammer with 400 durability, 6 damage, and 1.3 attack speed
    public static final RegistryObject<Item> HAMMER_ITEM = ITEMS.register("hammer",
            () -> new HammerItem(Tiers.IRON, 5f, -2.5f,
                    new Item.Properties().durability(400)));

    // Legendary Hammer with 2500 durability, 10 damage, and 1.2 attack speed
    public static final RegistryObject<Item> LEGENDARY_HAMMER_ITEM = ITEMS.register("legendary_hammer",
            () -> new HammerItem(Tiers.NETHERITE, 8f, -2.8f,
                    new Item.Properties().durability(2500)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
