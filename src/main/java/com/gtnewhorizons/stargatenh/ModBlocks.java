package com.gtnewhorizons.stargatenh;

import net.minecraft.block.Block;

import com.gtnewhorizons.stargatenh.common.block.StargateBlock;
import com.gtnewhorizons.stargatenh.common.block.StargateController;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

    static final Block stargateRingBlock = new StargateBlock().setBlockName("stargate_ring")
        .setBlockTextureName("stargatenh:ring_block");
    static final Block stargateChevronBlock = new StargateBlock().setBlockName("stargate_chevron")
        .setBlockTextureName("stargatenh:chevron_block");
    static final Block stargateControllerBlock = new StargateController();

    public static void init() {
        GameRegistry.registerBlock(stargateRingBlock, "stargate_ring");
        GameRegistry.registerBlock(stargateChevronBlock, "stargate_chevron");
        GameRegistry.registerBlock(stargateControllerBlock, "stargate_controller");
    }
}
