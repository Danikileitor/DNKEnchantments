package dnk.enchantments.enchantment;

import dnk.enchantments.DNKEnchantments;
import dnk.enchantments.config.CommonConfigs;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, DNKEnchantments.MODID);

    public static final RegistryObject<Enchantment> NIGHTVISION = CommonConfigs.ENCHANTMENT_NIGHTVISION.get() ? ENCHANTMENTS.register("nightvision", NightVision::new) : null;
    public static final RegistryObject<Enchantment> LEVITATION = CommonConfigs.ENCHANTMENT_LEVITATION.get() ? ENCHANTMENTS.register("levitation", Levitation::new) : null;
    public static final RegistryObject<Enchantment> JUMP = CommonConfigs.ENCHANTMENT_JUMP.get() ? ENCHANTMENTS.register("jump", Jump::new) : null;
    public static final RegistryObject<Enchantment> MAGNET = CommonConfigs.ENCHANTMENT_MAGNET.get() ? ENCHANTMENTS.register("magnet", Jump::new) : null;

    public static void register(IEventBus modEventBus) {
        ENCHANTMENTS.register(modEventBus);
    }
}
