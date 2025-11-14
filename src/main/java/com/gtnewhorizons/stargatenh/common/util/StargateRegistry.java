package com.gtnewhorizons.stargatenh.common.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;

public class StargateRegistry extends WorldSavedData {

    public static final String DATA_NAME = "StargateRegistry";

    private final Map<StargateAddress, BlockPos> registry = new HashMap<>();

    public StargateRegistry() {
        super(DATA_NAME);
    }

    public StargateRegistry(String name) {
        super(name);
    }

    public void register(StargateAddress addr, BlockPos pos) {
        registry.put(addr, pos);
        markDirty();
    }

    public void unregister(StargateAddress addr) {
        registry.remove(addr);
        markDirty();
    }

    public BlockPos lookup(StargateAddress addr) {
        return registry.get(addr);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        registry.clear();
        NBTTagList list = nbt.getTagList("Entries", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound entry = list.getCompoundTagAt(i);

            int[] sigils = entry.getIntArray("addr");
            StargateAddress addr = new StargateAddress(sigils);

            int x = entry.getInteger("x");
            int y = entry.getInteger("y");
            int z = entry.getInteger("z");

            registry.put(addr, new BlockPos(x, y, z));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        NBTTagList list = new NBTTagList();

        for (Map.Entry<StargateAddress, BlockPos> e : registry.entrySet()) {
            NBTTagCompound tag = new NBTTagCompound();

            tag.setIntArray("addr", e.getKey().sigils);

            BlockPos pos = e.getValue();
            tag.setInteger("x", pos.x);
            tag.setInteger("y", pos.y);
            tag.setInteger("z", pos.z);

            list.appendTag(tag);
        }

        nbt.setTag("Entries", list);
    }

    public static StargateRegistry get(World world) {
        MapStorage storage = world.mapStorage;
        StargateRegistry data = (StargateRegistry) storage.loadData(StargateRegistry.class, StargateRegistry.DATA_NAME);

        if (data == null) {
            data = new StargateRegistry();
            storage.setData(StargateRegistry.DATA_NAME, data);
        }

        return data;
    }
}
