package dnk.enchantments.item;

import dnk.enchantments.DNKEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, DNKEnchantments.MODID);

    public static RegistryObject<CreativeModeTab> DNKFORGE_TAB = CREATIVE_MODE_TABS.register("dnkenchantments_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(Items.ENCHANTED_BOOK))
                    .title(Component.translatable("creativemodetab.dnkenchantments_tab"))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}