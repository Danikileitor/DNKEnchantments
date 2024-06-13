package dnk.enchantments.enchantment;

import dnk.enchantments.DNKEnchantments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DNKEnchantments.MODID);

    public static final RegistryObject<Enchantment> NIGHTVISION = ENCHANTMENTS.register("nightvision", () -> new NightVision(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.ARMOR_HEAD, EquipmentSlot.MAINHAND));

    public static void register(IEventBus modEventBus) {
        ENCHANTMENTS.register(modEventBus);
    }
}
