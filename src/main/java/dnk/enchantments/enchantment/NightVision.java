package dnk.enchantments.enchantment;

import java.util.Set;

import dnk.enchantments.DNKEnchantments;
import dnk.enchantments.config.CommonConfigs;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = DNKEnchantments.MODID)
public class NightVision extends Enchantment {

    public NightVision(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public NightVision() {
        super(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_HEAD, new EquipmentSlot[]{EquipmentSlot.HEAD});
    }
    
    @Override
    public int getMaxLevel(){
        return 1;
    }
    
    @Override
    public boolean isAllowedOnBooks() {
        return CommonConfigs.ENCHANTMENT_NIGHTVISION.get();
    }
    
    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return CommonConfigs.ENCHANTMENT_NIGHTVISION.get();
    }

    @Override
    public boolean isTradeable() {
        return CommonConfigs.ENCHANTMENT_NIGHTVISION.get();
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e){
        if (EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.NIGHTVISION.get(), e.player.getItemBySlot(EquipmentSlot.HEAD)) > 0) {
            MobEffectInstance playerEffect = new MobEffectInstance(MobEffects.NIGHT_VISION, 210, 100, false, false, false);
            e.player.addEffect(playerEffect);
        }
    }
}