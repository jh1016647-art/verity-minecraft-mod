package com.verity;

import com.verity.block.VerityBoxBlock;
import com.verity.entity.VerityEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerityMod implements ModInitializer {
    public static final String MOD_ID = "verity";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Block VERITY_BOX = Registry.register(
            Registries.BLOCK,
            new Identifier(MOD_ID, "verity_box"),
            new VerityBoxBlock(AbstractBlock.Settings.of(Material.METAL))
    );

    public static final Item VERITY_BOX_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier(MOD_ID, "verity_box"),
            new BlockItem(VERITY_BOX, new Item.Settings())
    );

    public static final EntityType<VerityEntity> VERITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(MOD_ID, "verity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VerityEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6F, 1.8F))
                    .build()
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Verity has awakened.");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> {
            entries.add(VERITY_BOX_ITEM);
        });

        // Register attributes on the entity class with the current entity type
        // The actual attribute setup is handled by the entity class.
    }
}
