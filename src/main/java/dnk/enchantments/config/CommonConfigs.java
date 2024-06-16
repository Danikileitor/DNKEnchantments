package dnk.enchantments.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> ENCHANTMENT_JUMP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENCHANTMENT_LEVITATION;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENCHANTMENT_MAGNET;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENCHANTMENT_NIGHTVISION;

    static{
        BUILDER.push("Common configs for DNK Enchantments");

        ENCHANTMENT_JUMP = BUILDER.comment("Enable Jump Enchantment").define("Enchantment Jump Enabled", true);
        ENCHANTMENT_LEVITATION = BUILDER.comment("Enable Levitation Enchantment").define("Enchantment Levitation Enabled", true);
        ENCHANTMENT_MAGNET = BUILDER.comment("Enable Magnet Enchantment").define("Enchantment Magnet Enabled", true);
        ENCHANTMENT_NIGHTVISION = BUILDER.comment("Enable Night Vision Enchantment").define("Enchantment Night Vision Enabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
