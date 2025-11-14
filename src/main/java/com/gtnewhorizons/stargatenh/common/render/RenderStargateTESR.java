package com.gtnewhorizons.stargatenh.common.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.gtnewhorizons.stargatenh.common.tileentity.TileStargateController;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderStargateTESR extends TileEntitySpecialRenderer {

    private static final ResourceLocation MODEL = new ResourceLocation("stargatenh", "models/stargate.obj");
    private static final ResourceLocation TEXTURE = new ResourceLocation("stargatenh", "textures/models/stargate.png");
    private static final ResourceLocation RING_TEXTURE = new ResourceLocation(
        "stargatenh",
        "textures/models/sigil_wheel.png");

    private final IModelCustom model;

    public RenderStargateTESR() {
        model = AdvancedModelLoader.loadModel(MODEL);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks) {
        if (!(te instanceof TileStargateController controller)) return;

        int facing = controller.facing;
        if (facing == -1) facing = controller.setFacing();

        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y, z + 0.5);

        float angle = switch (facing) {
            case 4 -> 90F;
            case 3 -> 180F;
            case 2 -> -90F;
            default -> 0F;
        };
        GL11.glRotatef(angle, 0F, 1F, 0F);

        bindTexture(TEXTURE);
        model.renderAll();

        // Ring
        GL11.glPushMatrix();

        GL11.glTranslatef(-0.4F, 2.5F, 0F);
        GL11.glRotatef(270F, 0F, 1F, 0F);
        GL11.glScalef(1.8F, 1.8F, 1.8F);

        float ringRotation = controller.prevRingRotation
            + (controller.ringRotation - controller.prevRingRotation) * partialTicks;

        GL11.glRotatef(ringRotation, 0F, 0F, 1F);

        bindTexture(RING_TEXTURE);

        drawRingPlane();

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private void drawRingPlane() {
        Tessellator t = Tessellator.instance;
        t.startDrawing(GL11.GL_TRIANGLE_FAN);

        float radius = 1.4F;
        int segments = 64;

        t.addVertexWithUV(0, 0, 0, 0.5, 0.5);

        for (int i = 0; i <= segments; i++) {
            double theta = 2 * Math.PI * (i / (double) segments);
            float x = (float) Math.cos(theta) * radius;
            float y = (float) Math.sin(theta) * radius;

            double u = 0.5 + x / (radius * 2);
            double v = 0.5 + y / (radius * 2);

            t.addVertexWithUV(x, y, 0, u, v);
        }

        t.draw();
    }
}
