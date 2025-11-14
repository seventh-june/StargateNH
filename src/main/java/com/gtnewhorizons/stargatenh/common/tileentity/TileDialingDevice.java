package com.gtnewhorizons.stargatenh.common.tileentity;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;

import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.drawable.DynamicDrawable;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.InteractionSyncHandler;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.CycleButtonWidget;
import com.cleanroommc.modularui.widgets.layout.Row;
import com.gtnewhorizon.gtnhlib.blockpos.BlockPos;
import com.gtnewhorizons.stargatenh.client.ui.UITextures;
import com.gtnewhorizons.stargatenh.common.util.StargateAddress;
import com.gtnewhorizons.stargatenh.common.util.StargateRegistry;

public class TileDialingDevice extends TileEntity implements IGuiHolder<PosGuiData> {

    private final int[] address = { 0, 0, 0, 0, 0, 0, 0 };
    private boolean hasAddress = false;
    TileStargateController controller;

    public void connectGate() {
        if (worldObj.getTileEntity(xCoord + 4, yCoord, zCoord) instanceof TileStargateController c) controller = c;
        else if (worldObj.getTileEntity(xCoord - 4, yCoord, zCoord) instanceof TileStargateController c) controller = c;
        else if (worldObj.getTileEntity(xCoord, yCoord, zCoord + 4) instanceof TileStargateController c) controller = c;
        else if (worldObj.getTileEntity(xCoord, yCoord, zCoord - 4) instanceof TileStargateController c) controller = c;
    }

    @Override
    public ModularPanel buildUI(PosGuiData data, PanelSyncManager syncManager, UISettings settings) {
        ModularPanel panel = new ModularPanel("panel").size(176, 100);
        if (controller == null) {
            panel.child(
                IKey.str("Link Failed!")
                    .asWidget()
                    .marginLeft(5)
                    .marginRight(5)
                    .marginTop(5)
                    .marginBottom(-15));
        } else if (hasAddress) {
            panel.child(
                IKey.str("Stargate operational.")
                    .asWidget()
                    .marginLeft(5)
                    .marginRight(5)
                    .marginTop(5)
                    .marginBottom(-15));
        } else {
            StargateRegistry reg = StargateRegistry.get(worldObj);
            BooleanSyncValue isUnique = new BooleanSyncValue(() -> reg.lookup(new StargateAddress(address)) == null);

            IntSyncValue chevron1 = new IntSyncValue(() -> address[0], i -> address[0] = i);
            IntSyncValue chevron2 = new IntSyncValue(() -> address[1], i -> address[1] = i);
            IntSyncValue chevron3 = new IntSyncValue(() -> address[2], i -> address[2] = i);
            IntSyncValue chevron4 = new IntSyncValue(() -> address[3], i -> address[3] = i);
            IntSyncValue chevron5 = new IntSyncValue(() -> address[4], i -> address[4] = i);
            IntSyncValue chevron6 = new IntSyncValue(() -> address[5], i -> address[5] = i);
            IntSyncValue chevron7 = new IntSyncValue(() -> address[6], i -> address[6] = i);

            syncManager.syncValue("isUnique", isUnique);
            syncManager.syncValue("chevron1", chevron1);
            syncManager.syncValue("chevron2", chevron2);
            syncManager.syncValue("chevron3", chevron3);
            syncManager.syncValue("chevron4", chevron4);
            syncManager.syncValue("chevron5", chevron5);
            syncManager.syncValue("chevron6", chevron6);
            syncManager.syncValue("chevron7", chevron7);

            panel.child(
                IKey.str("Set Address for this Stargate")
                    .asWidget()
                    .marginLeft(5)
                    .marginRight(5)
                    .marginTop(5)
                    .marginBottom(-15));

            Row sigils = new Row();
            panel.child(
                sigils.size(156, 16)
                    .marginTop(20)
                    .marginLeft(14)
                    .childPadding(6));

            sigils.child(
                new CycleButtonWidget().syncHandler("chevron1")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron1.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron2")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron2.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron3")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron3.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron4")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron4.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron5")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron5.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron6")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron6.getIntValue()))));
            sigils.child(
                new CycleButtonWidget().syncHandler("chevron7")
                    .size(16, 16)
                    .background(UITextures.SIGIL_BG)
                    .hoverBackground(UITextures.SIGIL_BG_ACTIVE)
                    .length(5)
                    .overlay(new DynamicDrawable(() -> UITextures.getSigil(chevron7.getIntValue()))));

            panel.child(
                new ButtonWidget<>().marginTop(48)
                    .marginLeft(8)
                    .size(18, 18)
                    .tooltip(t -> t.add("Generate a random unused address"))
                    .overlay(UITextures.OVERLAY_RANDOM)
                    .syncHandler(new InteractionSyncHandler().setOnMousePressed(mouseData -> {
                        Random rng = new Random();
                        do {
                            chevron1.setIntValue(rng.nextInt(5));
                            chevron2.setIntValue(rng.nextInt(5));
                            chevron3.setIntValue(rng.nextInt(5));
                            chevron4.setIntValue(rng.nextInt(5));
                            chevron5.setIntValue(rng.nextInt(5));
                            chevron6.setIntValue(rng.nextInt(5));
                            chevron7.setIntValue(rng.nextInt(5));
                            isUnique.updateCacheFromSource(false);
                        } while (!isUnique.getBoolValue());
                    })));

            panel.child(
                IKey.dynamic(() -> isUnique.getBoolValue() ? "Address is available" : "Address already in use")
                    .asWidget()
                    .marginLeft(36)
                    .marginTop(52));

            panel.child(
                new ButtonWidget<>().marginTop(48)
                    .marginLeft(148)
                    .size(18, 18)
                    .tooltip(t -> t.add("Lock in stargate address"))
                    .setEnabledIf(ignored -> isUnique.getBoolValue())
                    .overlay(UITextures.OVERLAY_CHECK)
                    .syncHandler(new InteractionSyncHandler().setOnMousePressed(mouseData -> {
                        reg.register(new StargateAddress(address), new BlockPos(xCoord, yCoord, zCoord));
                        hasAddress = true;
                        panel.closeIfOpen();
                    })));
        }
        return panel;
    }

}
