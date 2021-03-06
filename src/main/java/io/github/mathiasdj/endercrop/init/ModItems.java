package io.github.mathiasdj.endercrop.init;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import io.github.mathiasdj.endercrop.item.ItemEnderSeeds;

public class ModItems {

    public static final ItemEnderSeeds ENDER_SEEDS = new ItemEnderSeeds();

    public static void init() {
        GameRegistry.register(ENDER_SEEDS);
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        ModelLoader.setCustomModelResourceLocation(ENDER_SEEDS, 0, new ModelResourceLocation(ENDER_SEEDS.getRegistryName().toString()));
    }
}
