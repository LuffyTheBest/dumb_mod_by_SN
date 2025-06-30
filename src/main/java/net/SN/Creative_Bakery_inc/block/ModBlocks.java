package net.SN.Creative_Bakery_inc.block;

import net.SN.Creative_Bakery_inc.creativebakeryinc;
import net.SN.Creative_Bakery_inc.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;



import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, creativebakeryinc.MOD_ID);

    public static final RegistryObject<Block> DUSTY_HAMMER = registerBlock("dusty_hammer",
            () -> new DustyHammerBlock(BlockBehaviour.Properties
                    .of()
                    .sound(SoundType.GLASS)
                    .strength(0.3f)
                    .noOcclusion()
                    .isValidSpawn((state, world, pos, entityType) -> false)
                    .isRedstoneConductor((state, world, pos) -> false)
                    .isSuffocating((state, world, pos) -> false)
                    .isViewBlocking((state, world, pos) -> false)));

    // Remove this line as it's causing issues
    // private static net.SN.Creative_Bakery_inc.item.ModItems ModItems;

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        // Use ModItems directly instead of the shadowed field
        return net.SN.Creative_Bakery_inc.item.ModItems.ITEMS.register(name,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}