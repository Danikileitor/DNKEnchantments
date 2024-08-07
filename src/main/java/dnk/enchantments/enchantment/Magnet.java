package dnk.enchantments.enchantment;

import dnk.enchantments.DNKEnchantments;
import dnk.enchantments.config.CommonConfigs;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = "dnkenchantments")
public class Magnet extends Enchantment {
    public Magnet(Enchantment.Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    public Magnet() {
        super(Enchantment.Rarity.COMMON, EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[] { EquipmentSlot.CHEST });
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    public boolean isAllowedOnBooks() {
        return CommonConfigs.ENCHANTMENT_MAGNET.get();
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return CommonConfigs.ENCHANTMENT_MAGNET.get();
    }

    @Override
    public boolean isTradeable() {
        return CommonConfigs.ENCHANTMENT_MAGNET.get();
    }

    @Override
    public boolean isTreasureOnly() {
        return false;
    }

    protected static List<Entity> getEntitiesInCircumference(Level world, Player player, double horizontal,
            double vertical, boolean select) {
        List<?> list = world.getEntities((Entity) player,
                player.getBoundingBox().inflate(horizontal, vertical, horizontal));
        if (null == list || list.isEmpty())
            return null;
        List<Entity> ret = new ArrayList<>();
        for (Object o : list) {
            Entity e = (Entity) o;
            if (!e.isAlive())
                continue;
            boolean doCollide = false;
            if (!DNKEnchantments._itemIds.isEmpty()) {
                if (e instanceof net.minecraft.world.entity.ExperienceOrb) {
                    doCollide = true;
                } else if (e instanceof ItemEntity) {
                    ItemStack itemstack = ((ItemEntity) e).getItem();
                    for (Object item : DNKEnchantments._itemIds) {
                        if (!itemstack.getItem().getClass().equals(item.getClass()))
                            continue;
                        if (item instanceof BlockItem) {
                            Block b1 = ((BlockItem) itemstack.getItem()).getBlock();
                            Block b2 = ((BlockItem) item).getBlock();
                            if (b1.equals(b2)) {
                                doCollide = true;
                                break;
                            }
                            continue;
                        }
                        doCollide = true;
                    }
                    if (!select)
                        doCollide = !doCollide;
                }
            } else if (e instanceof ItemEntity || e instanceof net.minecraft.world.entity.ExperienceOrb) {
                doCollide = true;
            }
            if (!doCollide)
                continue;
            ret.add(e);
        }
        return ret.isEmpty() ? null : ret;
    }

    protected static void startPickup(Level world, Player player, List<Entity> list, boolean isThreadClient) {
        ServerLevel ws = (world instanceof ServerLevel) ? (ServerLevel) world : null;
        for (Entity entity : list) {
            if (!entity.isAlive())
                continue;
            if (isThreadClient) {
                entity = ws.getEntity(entity.getId());
                if (null == entity)
                    continue;
            }
            entity.playerTouch(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        Level world1;
        List<Entity> list1;
        Level world2;
        List<Entity> list2;
        Level world3;
        List<Entity> list3;
        switch (EnchantmentHelper.getTagEnchantmentLevel((Enchantment) ModEnchantments.MAGNET.get(), e.player
                .getItemBySlot(EquipmentSlot.CHEST))) {
            case 1:
                if (!TickEvent.Phase.END.equals(e.phase))
                    return;
                world1 = e.player.getCommandSenderWorld();
                list1 = getEntitiesInCircumference(world1, e.player, 10, 10, false);
                if (null == list1)
                    return;
                startPickup(world1, e.player, list1, false);
                break;
            case 2:
                if (!TickEvent.Phase.END.equals(e.phase))
                    return;
                world2 = e.player.getCommandSenderWorld();
                list2 = getEntitiesInCircumference(world2, e.player, 50, 50, false);
                if (null == list2)
                    return;
                startPickup(world2, e.player, list2, false);
                break;
            case 3:
                if (!TickEvent.Phase.END.equals(e.phase))
                    return;
                world3 = e.player.getCommandSenderWorld();
                list3 = getEntitiesInCircumference(world3, e.player, 100, 100, false);
                if (null == list3)
                    return;
                startPickup(world3, e.player, list3, false);
                break;
        }
    }
}