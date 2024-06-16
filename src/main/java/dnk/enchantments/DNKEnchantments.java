package dnk.enchantments;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dnk.enchantments.config.CommonConfigs;
import dnk.enchantments.enchantment.ModEnchantments;
import dnk.enchantments.item.ModCreativeModeTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DNKEnchantments.MODID)
public class DNKEnchantments {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "dnkenchantments";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    //Esto es para MAGNET
    public static String itemIds = "";
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final List<Object> _itemIds = new ArrayList();

    public DNKEnchantments() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        
        // Register configs
        ModLoadingContext.get().registerConfig(Type.COMMON, CommonConfigs.SPEC, "DNKEnchantments.toml");

        // Register todas las cosas por aquí
        ModCreativeModeTabs.register(modEventBus);
        ModEnchantments.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    //Esto es para MAGNET
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static List<Object> idStringToArray(String s, boolean isBlock) {
        try {
            IForgeRegistry registry = isBlock ? ForgeRegistries.BLOCKS : ForgeRegistries.ITEMS;
            List<Object> l = new ArrayList();
            String[] ss = s.split(",");
            for (String i : ss) {
                Object b = null;
                i = i.trim();
                if (!i.isEmpty()) {
                    b = registry.getValue(new ResourceLocation(i));
                    if (Blocks.AIR == b || Items.AIR == b) {
                        i = "minecraft:" + i;
                        b = registry.getValue(new ResourceLocation(i));
                    }
                    if (Blocks.AIR != b && Items.AIR != b)
                        l.add(b);
                }
            }
            return l;
        } catch (Exception e) {
            LOGGER.error("idStringToArray", e);
            return Collections.emptyList();
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        _itemIds.addAll(idStringToArray(itemIds, false));//Esto es para MAGNET
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ModCreativeModeTabs.DNKFORGE_TAB.get()) {
            for (RegistryObject<Enchantment> encantamiento : ModEnchantments.ENCHANTMENTS.getEntries()) {
                for (int nivel = encantamiento.get().getMinLevel(); nivel <= encantamiento.get()
                        .getMaxLevel(); nivel++) {
                    event.accept(EnchantedBookItem
                            .createForEnchantment(new EnchantmentInstance(encantamiento.get(), nivel)));
                }
            }
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
