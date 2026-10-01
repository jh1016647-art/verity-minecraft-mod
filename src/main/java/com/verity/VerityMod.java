package com.verity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.verity.entity.VerityEntity;
import com.verity.block.VerityBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;

public class VerityMod implements ModInitializer {
    public static final String MOD_ID = "verity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Entity Types
    public static EntityType<VerityEntity> VERITY = Registry.register(
        Registries.ENTITY_TYPE,
        new Identifier(MOD_ID, "verity"),
        FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VerityEntity::new)
            .dimensions(EntityDimensions.fixed(0.6f, 1.8f))
            .build()
    );

    // Blocks
    public static Block VERITY_BOX = Registry.register(
        Registries.BLOCK,
        new Identifier(MOD_ID, "verity_box"),
        new VerityBoxBlock(AbstractBlock.Settings.of(Material.METAL))
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Verity Mod");
        LOGGER.info("Verity entity registered");
        LOGGER.info("Verity box block registered");
    }
}
