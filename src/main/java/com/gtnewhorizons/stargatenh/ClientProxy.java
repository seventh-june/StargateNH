package com.gtnewhorizons.stargatenh;

import com.gtnewhorizons.stargatenh.common.render.RenderStargateTESR;
import com.gtnewhorizons.stargatenh.common.tileentity.TileStargateController;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(TileStargateController.class, new RenderStargateTESR());
        super.init(event);
    }
}
