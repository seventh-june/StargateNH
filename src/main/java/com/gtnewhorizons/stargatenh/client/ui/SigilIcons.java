package com.gtnewhorizons.stargatenh.client.ui;

import com.cleanroommc.modularui.drawable.UITexture;
import com.gtnewhorizons.stargatenh.StargateNH;

public class SigilIcons {

    public static final UITexture SIGIL_BG = UITexture.fullImage(StargateNH.MODID, "gui/sigil_bg");
    public static final UITexture SIGIL_BG_ACTIVE = UITexture.fullImage(StargateNH.MODID, "gui/sigil_bg_active");

    public static final UITexture SIGIL_0 = UITexture.fullImage(StargateNH.MODID, "gui/sigil_0");
    public static final UITexture SIGIL_1 = UITexture.fullImage(StargateNH.MODID, "gui/sigil_1");
    public static final UITexture SIGIL_2 = UITexture.fullImage(StargateNH.MODID, "gui/sigil_2");
    public static final UITexture SIGIL_3 = UITexture.fullImage(StargateNH.MODID, "gui/sigil_3");
    public static final UITexture SIGIL_4 = UITexture.fullImage(StargateNH.MODID, "gui/sigil_4");

    public static UITexture getSigil(int i) {
        return switch (i) {
            case 1 -> SIGIL_1;
            case 2 -> SIGIL_2;
            case 3 -> SIGIL_3;
            case 4 -> SIGIL_4;
            default -> SIGIL_0;
        };
    }
}
