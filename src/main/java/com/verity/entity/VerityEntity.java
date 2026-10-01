package com.verity.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class VerityEntity extends HostileEntity {
    private int voiceChatCooldown = 0;
    private boolean hasDetectedPlayer = false;

    public VerityEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        // Passive behaviors
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.75D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));

        // Target player
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createVerityAttributes() {
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
            .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5D);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.voiceChatCooldown > 0) {
            this.voiceChatCooldown--;
        }

        if (!this.getWorld().isClient && this.getTarget() != null) {
            if (!this.hasDetectedPlayer) {
                this.hasDetectedPlayer = true;
                this.triggerVoiceChat();
            }
        }
    }

    private void triggerVoiceChat() {
        if (this.voiceChatCooldown <= 0) {
            // Trigger voice chat sound/event
            // This would integrate with a voice chat system
            // For now, we'll use particle effects to indicate communication
            this.voiceChatCooldown = 200; // 10 seconds cooldown
        }
    }

    public boolean shouldSpeedUp() {
        PlayerEntity player = this.getTarget();
        if (player == null) return false;
        return this.squaredDistanceTo(player) < 100.0D; // Closer than 10 blocks
    }

    @Override
    protected void updateVelocity() {
        super.updateVelocity();
        if (this.shouldSpeedUp()) {
            this.velocityModified = true;
        }
    }
}
