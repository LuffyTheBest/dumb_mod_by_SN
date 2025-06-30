package net.SN.Creative_Bakery_inc.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.tags.BlockTags;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;

public class HammerItem extends TieredItem {
    private final float attackDamage;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public HammerItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(tier, properties);
        this.attackDamage = attackDamage + tier.getAttackDamageBonus();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier",
                this.attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier",
                attackSpeed, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        // Instant break for obsidian
        if (state.is(Blocks.OBSIDIAN)) {
            return 1000.0F;
        }

        // Normal wood breaking speeds
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return getTier() == Tiers.IRON ? 6.0F :
                    getTier() == Tiers.NETHERITE ? 8.0F :
                            1.0F;
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        // Make sure we can get drops from obsidian
        if (state.is(Blocks.OBSIDIAN)) {
            return getTier().getLevel() >= 3; // Diamond level or higher
        }

        // Handle normal axe drops
        if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
            return getTier().getLevel() >= 0; // Any tier can mine wood
        }

        return super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, net.minecraft.world.level.Level level, BlockState state, net.minecraft.core.BlockPos pos, LivingEntity miningEntity) {
        if (state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(Blocks.OBSIDIAN)) {
            stack.hurtAndBreak(1, miningEntity, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }
}

