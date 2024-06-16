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

    public static final RegistryObject<Enchantment> NIGHTVISION = ENCHANTMENTS.register("nightvision", NightVision::new);
    public static final RegistryObject<Enchantment> LEVITATION = ENCHANTMENTS.register("levitation", Levitation::new);
    public static final RegistryObject<Enchantment> JUMP = ENCHANTMENTS.register("jump", Jump::new);
    public static final RegistryObject<Enchantment> MAGNET = ENCHANTMENTS.register("magnet", Jump::new);

    public static void register(IEventBus modEventBus) {
        if (!CommonConfigs.ENCHANTMENT_NIGHTVISION.get()) modEventBus.unregister(NIGHTVISION);
        if (!CommonConfigs.ENCHANTMENT_LEVITATION.get()) modEventBus.unregister(LEVITATION);
        if (!CommonConfigs.ENCHANTMENT_JUMP.get()) modEventBus.unregister(JUMP);
        if (!CommonConfigs.ENCHANTMENT_MAGNET.get()) modEventBus.unregister(MAGNET);

        ENCHANTMENTS.register(modEventBus);
    }
}
