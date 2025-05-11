package com.comical.examplemod.blocks.blockMoroon;

import com.comical.examplemod.blocks.BlockBase;
import net.minecraft.block.material.Material;

public class BlockMoroonBase extends BlockBase {
    public BlockMoroonBase(String name, Material material) {
        super(name, material);
        setResistance(1.0F);
    }
}
