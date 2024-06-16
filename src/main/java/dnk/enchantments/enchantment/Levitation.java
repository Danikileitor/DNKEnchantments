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
public class Levitation extends Enchantment {

    public Levitation(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public Levitation() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[] { EquipmentSlot.FEET });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        switch (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.LEVITATION.get(),
                e.player.getItemBySlot(EquipmentSlot.FEET))) {
            case 1:
                e.player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 0, false, false, false));
                break;

            case 2:
                e.player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 10, false, false, false));
                break;

            case 3:
                e.player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 100, false, false, false));
                break;

            default:
                break;
        }
    }
}