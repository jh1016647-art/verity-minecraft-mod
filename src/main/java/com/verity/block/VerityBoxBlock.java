package com.verity.block;

import com.verity.VerityMod;
import com.verity.entity.VerityEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VerityBoxBlock extends Block {
    public VerityBoxBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            spawnVerity(world, pos, player);
            world.breakBlock(pos, false);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            spawnVerity(world, pos, player);
            world.breakBlock(pos, false);
        }
        super.onSteppedOn(world, pos, state, player);
    }

    private void spawnVerity(World world, BlockPos pos, PlayerEntity player) {
        VerityEntity verity = new VerityEntity(VerityMod.VERITY, world);
        verity.setPos(pos.getX() + 0.5D, pos.getY() + 1.0D, pos.getZ() + 0.5D);
        verity.setTarget(player);
        world.spawnEntity(verity);
    }
}
