package com.enderio.base.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.enderio.base.common.capability.EIOCapabilities;
import com.enderio.base.common.capability.capacitors.ICapacitorData;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;

/**
 * Helper class for Capacitors
 */
public class CapacitorUtil {
    /**
     * Static maps with specializations for the "basic"
     */
    private static ArrayList<String> types = new ArrayList<>();
    public static Map<String, Float> BASIC = new HashMap<>();
    public static Map<String, Float> DOUBLE = new HashMap<>();
    public static Map<String, Float> OCTADIC = new HashMap<>();
    static {
        BASIC.put(ICapacitorData.ALL_ENERGY_CONSUMPSTION, 1.0F);
        BASIC.put(ICapacitorData.ALL_PRODUCTION_SPEED, 1.0F);
        DOUBLE.put(ICapacitorData.ALL_ENERGY_CONSUMPSTION, 2.0F);
        DOUBLE.put(ICapacitorData.ALL_PRODUCTION_SPEED, 2.0F);
        OCTADIC.put(ICapacitorData.ALL_ENERGY_CONSUMPSTION, 4.0F);
        OCTADIC.put(ICapacitorData.ALL_PRODUCTION_SPEED, 4.0F);
        types.add(ICapacitorData.ALL_ENERGY_CONSUMPSTION);
        types.add(ICapacitorData.ALL_PRODUCTION_SPEED);
        types.add(ICapacitorData.ALLOY_ENERGY_CONSUMPSTION);
        types.add(ICapacitorData.ALLOY_ENERGY_CONSUMPSTION);
    }

    /**
     * Returns a random type from the list for loot capacitors.
     * @return
     */
    public static String getRandomType() {
        return types.get(new Random().nextInt(types.size()));
    }
    
    /**
     * Adds a tooltip for loot capacitors based on it's stats.
     * @param stack
     * @param tooltipComponents
     */
    public static void getTooltip(ItemStack stack, List<Component> tooltipComponents) {
        stack.getCapability(EIOCapabilities.CAPACITOR).ifPresent(cap -> {
            TranslatableComponent t = new TranslatableComponent(getFlavor(cap.getFlavor()),
                    getGradeText(cap.getSpecializations().values().stream().findFirst().get()),
                    getTypeText(cap.getSpecializations().keySet().stream().findFirst().get()),
                    getBaseText(cap.getBase()));
            tooltipComponents.add(t);
        });
    }
    
    //TODO depending on direction
    private static String getFlavor(int flavor) {
        return "description.enderio.capacitor.flavor."+flavor;
    }
    
    //TODO depending on direction
    private static TranslatableComponent getBaseText(float base) {
        TranslatableComponent t = new TranslatableComponent("description.enderio.capacitor.base." + (int)Math.ceil(base));
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }
    
    //TODO depending on direction
    private static TranslatableComponent getTypeText(String type) {
        TranslatableComponent t = new TranslatableComponent("description.enderio.capacitor.type." + type);
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }
    
    //TODO depending on direction
    private static TranslatableComponent getGradeText(float grade) {
        TranslatableComponent t = new TranslatableComponent("description.enderio.capacitor.grade." + (int)Math.ceil(grade));
        t.withStyle(ChatFormatting.ITALIC);
        return t;
    }
}
