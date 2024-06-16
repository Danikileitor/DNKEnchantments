package dnk.enchantments.enchantment;

import dnk.enchantments.DNKEnchantments;
import dnk.enchantments.config.CommonConfigs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = DNKEnchantments.MODID)
public class Jump extends Enchantment {

    public Jump(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public Jump() {
        super(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] { EquipmentSlot.FEET });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public boolean canEnchant(ItemStack pStack) {
        return CommonConfigs.ENCHANTMENT_JUMP.get() ? super.canEnchant(pStack) : false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return CommonConfigs.ENCHANTMENT_JUMP.get() ? super.canApplyAtEnchantingTable(stack): false;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        switch (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.JUMP.get(),
                e.player.getItemBySlot(EquipmentSlot.FEET))) {
            case 1:
                e.player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 10, false, false, false));
                break;

            case 2:
                e.player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 50, false, false, false));
                break;

            case 3:
                e.player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 100, false, false, false));
                break;

            default:
                break;
        }
    }
}