package com.gtnewhorizons.stargatenh.common.util;

import java.util.Arrays;

public final class StargateAddress {

    public final int[] sigils;

    public StargateAddress(int[] sigils) {
        this.sigils = Arrays.copyOf(sigils, 7);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StargateAddress other)) return false;
        return Arrays.equals(this.sigils, other.sigils);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(sigils);
    }

    @Override
    public String toString() {
        return Arrays.toString(sigils);
    }
}
