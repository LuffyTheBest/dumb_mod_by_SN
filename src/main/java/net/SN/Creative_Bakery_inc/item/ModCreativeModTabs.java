package net.SN.Creative_Bakery_inc.item;

import net.SN.Creative_Bakery_inc.creativebakeryinc;
import net.SN.Creative_Bakery_inc.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, creativebakeryinc.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BAKERY_TAB = CREATIVE_MODE_TABS.register("bakery_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.HAMMER_ITEM.get())) // Use the standalone hammer item
                    .title(Component.translatable("Creative Bakery Inc"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.HAMMER_ITEM.get());
                        output.accept(ModItems.LEGENDARY_HAMMER_ITEM.get());
                        output.accept(ModBlocks.DUSTY_HAMMER.get());
                        output.accept(ModItems.HAMMER_OF_JUSTICE.get());
                        // Add more items/blocks here as needed
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}