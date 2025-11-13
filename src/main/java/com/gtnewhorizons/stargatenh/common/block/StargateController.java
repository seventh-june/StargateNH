package com.gtnewhorizons.stargatenh.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gtnewhorizons.stargatenh.common.tileentity.TileStargateController;

public class StargateController extends BlockContainer {

    public StargateController() {
        super(Material.iron);
        setBlockTextureName("stargatenh:chevron_block");
        setBlockName("stargate_controller");
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileStargateController();
    }
}
