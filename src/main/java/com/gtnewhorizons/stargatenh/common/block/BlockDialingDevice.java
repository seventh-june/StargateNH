package com.gtnewhorizons.stargatenh.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.cleanroommc.modularui.factory.GuiFactories;
import com.gtnewhorizons.stargatenh.common.tileentity.TileDialingDevice;

public class BlockDialingDevice extends BlockContainer {

    public BlockDialingDevice() {
        super(Material.iron);
        setBlockName("dialing_device");
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (worldIn.getTileEntity(x, y, z) instanceof TileDialingDevice dialingDevice) dialingDevice.connectGate();
        if (!worldIn.isRemote) GuiFactories.tileEntity()
            .open(player, x, y, z);
        return true;
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileDialingDevice();
    }
}
