package com.verity.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hand.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.verity.VerityMod;

public class VerityBoxBlock extends Block {
    public VerityBoxBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            // Spawn Verity entity
            com.verity.entity.VerityEntity verity = new com.verity.entity.VerityEntity(VerityMod.VERITY, world);
            verity.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            verity.setTarget(player);
            world.spawnEntity(verity);

            // Remove the box block
            world.breakBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player != null) {
            // Spawn Verity when player steps on the box
            com.verity.entity.VerityEntity verity = new com.verity.entity.VerityEntity(VerityMod.VERITY, world);
            verity.setPos(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            verity.setTarget(player);
            world.spawnEntity(verity);

            // Remove the box block
            world.breakBlock(pos, false);
        }
        super.onSteppedOn(world, pos, state, player);
    }
}
